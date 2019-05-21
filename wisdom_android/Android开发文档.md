# 智慧教室 - Android开发文档

## 一、总体设计

此App是使用Android原生API开发，从总体上来说分为以下几个模块：

### 1、学生客户端

* **学生登录**(后台提前在数据库写入了数据，所以只提供登录功能)
* **学生人脸信息采集**

* **学生个人基本信息展示**
* **学生查看考勤信息**
* **学生上课睡觉检测提醒**
* **学生登录信息记录**
* 学生查看课堂作业
* 学生查看课堂预习内容
* 学生可以对课堂作出评价
* 学生查看并参加课外活动



### 2、教师客户端

* **教师登录**(后台提前在数据库写入了数据，所以只提供登录功能)
* **教师课堂考勤一分钟内完成**
* 教师布置作业
* 教师发布课堂预习内容
* 教师查看课堂评价
* 教师发布活动



## 二、使用技术

### 1、开发平台

* macOS Mojave 14.04  + AndroidStudio 3.2 + JDK1.8 

* Android 5.0 版本的手机、Android 6.0 版本的手机作为测试机器

### 2、使用的开源库

* android-async-http Android端的网络请求库
* OpenCV 3.4 AndroidSDK(独立)
* GSON：Google 的JSON解析库
* fastJSON：阿里巴巴的JSON解析库
* XUI：com.github.xuexiangjys贡献的一个AndroidUI组件库
* MPAndroidChart：Android原生图表库
* recyclerview：Androidsuport V7替代ListView的组件

### 3、版本控制与管理

* 使用Git作为项目管理工具
* 使用第三方Git服务商Github提供的Git服务

## 三、详细设计

### 1、学生/老师登录

![](https://s2.ax1x.com/2019/05/21/EzynMV.png)

> 因为我们在开发阶段做的只是测试，所以并没有提供注册的功能，而是直接访问学校教务信息数据库，在通过授权后直接提取学生、老师的信息，并把信息加入到我们自己的数据库中，所以未提供学生/老师注册



服务器端提供了登录接口，使用Android网络请求库携带登录信心向服务器发起请求，获取返回的JSON数据

![](https://s2.ax1x.com/2019/05/21/EzyCqS.png)

正如你所见，只要传入正确的account和password，如果是老师登录则传入status为2，如果是学生登录则status字段传1，就会以JSON返回登录者的个人信息：包括登录结果status，登录信息mag，返回数据id、姓名、性别、电话、邮箱、头像地址、班级、学院、微信ID等数据！

### 2、登录的Session保存

服务器端其他的接口都是要依赖于Session完成后续工作，所以我们自己封装了网络请求库，用于Session的保存：

```java
public class CookieUtils {

    private static List<Cookie> cookies;

    /* 返回cookies列表 */
    static List<Cookie> getCookies() {
        return cookies != null ? cookies : new ArrayList<>();
    }

    /* 设置cookies列表 */
    public static void setCookies(List<Cookie> cookies) {
        CookieUtils.cookies = cookies;
    }

    /* 存储cookie */
    public static void saveCookie(AsyncHttpClient client, Context context) {
        PersistentCookieStore cookieStore = new PersistentCookieStore(context);
        client.setCookieStore(cookieStore);
    }

    /* 得到cookie */
    public static List<Cookie> getCookie(Context context) {
        PersistentCookieStore cookieStore = new PersistentCookieStore(context);
        return cookieStore.getCookies();
    }

    /* 清除cookie */
    @SuppressWarnings("unused")
    public static void clearCookie(Context context) {
        PersistentCookieStore cookieStore = new PersistentCookieStore(context);
        cookieStore.clear();
    }
}

public class FinalAsyncHttpClient {

    private AsyncHttpClient client;
    /* 构造方法 */
    public FinalAsyncHttpClient() {
        client = new AsyncHttpClient();//实例化client
        client.setTimeout(5);//设置5秒超时
        // 获取cookie列表
        if (CookieUtils.getCookies() != null) {
            BasicCookieStore bcs = new BasicCookieStore();
            bcs.addCookies(CookieUtils.getCookies().toArray(
                    new Cookie[CookieUtils.getCookies().size()]));//得到cookie列表
            client.setCookieStore(bcs);//给client加载cookie
        }
    }

    /* 得到client对象方法 */
    public AsyncHttpClient getAsyncHttpClient() {
        return this.client;
    }
}
```

### 3、学生人脸信息采集

下面就是流程图，根据登录状态的不同

![](https://s2.ax1x.com/2019/05/21/Ezcrvt.png)

![](https://s2.ax1x.com/2019/05/21/EzyaqO.png)

学生登陆成功后会进行人联信息采集，其中采用了OpenCV的AndroidSDK开源库：

OpenCV的module方式引入：

![](https://s2.ax1x.com/2019/05/21/Ez6pWR.png)

初始化OpenCV摄像显示组件：

```java
private void initializeOpenCVDependencies() {
        try {
            InputStream is = getResources().openRawResource(R.raw.lbpcascade_frontalface); 							//OpenCV的人脸模型文件： lbpcascade_frontalface_improved
            File cascadeDir = getDir("cascade", Context.MODE_PRIVATE);
            File mCascadeFile = new File(cascadeDir, "lbpcascade_frontalface_improved.xml");
            FileOutputStream os = new FileOutputStream(mCascadeFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            os.close();
            // 加载cascadeClassifier
            cascadeClassifier = new CascadeClassifier(mCascadeFile.getAbsolutePath());
        } catch (Exception e) {
            Log.e(TAG, "Error loading cascade", e);
        }
        // 显示
        openCvCameraView.enableView();
}
```

手机摄像头权限申请与人脸信息采集：

```java
public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba(); //RGBA
        mGray = inputFrame.gray(); //单通道灰度图

        if (absoluteFaceSize == 0) {
            int height = mGray.rows();
            if (Math.round(height * 0.2f) > 0) {
                absoluteFaceSize = Math.round(height * 0.2f);
            }
        }

        //解决前置摄像头旋转显示问题
        Core.flip(mRgba, mRgba, 1); //旋转
        Core.flip(mGray, mGray, 1);

        //检测并显示
        MatOfRect faces = new MatOfRect();
        if (cascadeClassifier != null) {
            cascadeClassifier.detectMultiScale(mGray, faces, 1.1, 2, 2, new Size(absoluteFaceSize, absoluteFaceSize), new Size());
        }
        Rect[] facesArray = faces.toArray();
        if (facesArray.length > 0){
            pictureNum++;
            if(pictureNum < AppConfig.pictureCount){
                Bitmap mBitmap = Bitmap.createBitmap(mRgba.cols(), mRgba.rows(), Bitmap.Config.RGB_565);
                if(AppConfig.netWork){
                    Utils.matToBitmap(mRgba, mBitmap);
                    ImgSaveUtils.saveImageToGallery(getApplicationContext(), mBitmap, id, pwd);
                }
            }else{
                Intent intent = new Intent(getApplicationContext(), StudentAty.class);
                edit.putBoolean("isFirst", false);
                edit.commit();
                startActivity(intent);
                finish();
            }

            Log.i(AppConfig.TAG, "检测到人脸");
            Log.i(AppConfig.TAG, "accessToken:"+accessToken);
            for (Rect aFacesArray : facesArray) {
                Imgproc.rectangle(mRgba, aFacesArray.tl(), aFacesArray.br(), new Scalar(0, 255, 0, 255), 3);
            }
        }
        return mRgba;
    }


private void requestPermission() {
  Log.i(TAG,"requestPermission");
  if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
      != PackageManager.PERMISSION_GRANTED) {
    Log.i(TAG,"checkSelfPermission");
    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
      Log.i(TAG,"shouldShowRequestPermissionRationale");
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);

    } else {
      Log.i(TAG,"requestPermissions");
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
    }
  }
}
```

### 4、学生登录信息展示

如下图所示，轮播组采用了XUI的组件库，中间用于显示学生个人基本信息：

![](https://s2.ax1x.com/2019/05/21/Ez23m6.png)

```java
asyncHttpClient.post(AppConfig.loginAddress, params, new AsyncHttpResponseHandler() {
  @Override
  public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
    try{
      String retStr = new String(responseBody);

      Log.i(TAG, retStr);
      JSONObject retJson = JSONObject.parseObject(retStr);
      Integer status = retJson.getInteger("status");
      if(1 == status){
        edit.putString("retStr", retStr);
        if(edit.commit()) Log.i(TAG,"edit.commit() success!!!");

        XToast.success(getContext(), "登录成功").show();
        CookieUtils.setCookies(CookieUtils.getCookie(StdRegAty.this));
        //先结束掉此Activity
        startActivity(atyIntent);
        finish();
      }else if(0 == status){
        XToast.error(getContext(), "用户名或密码不正确").show();
      }
    }catch (Exception e){
      e.printStackTrace();
      XToast.error(getContext(), "Web端严重错误！！！").show();
    }finally {
      btn.setEnabled(true);
    }
  }

  @Override
  public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
    XToast.error(getContext(), "网络堵塞").show();
    btn.setEnabled(true);
  }
});
```



### 5、学生考勤信息查询

![](https://s2.ax1x.com/2019/05/21/EzWbl9.png)

这个同样也是访问服务器端提供的接口实现的，同时在recyclerview上面展示出考勤信息的条目，很清晰的展示出每节课的考勤信息。以每一节课为单位，如果要查看详细的得分情况，点击即可查看！

![](https://s2.ax1x.com/2019/05/21/EzReDP.png)

```java
asyncHttpClient.post(AppConfig.classInfoAddress, params, new AsyncHttpResponseHandler() {
  @Override
  public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
    String classInfoStr = new String(responseBody);
    Log.i(AppConfig.TAG, classInfoStr);
    //展示获得的数据
    JSONObject jsonObject = JSONObject.parseObject(classInfoStr);
    JSONArray data = jsonObject.getJSONArray("data");
    for (int i = 0; i < data.size(); i++) {
      AttendanceItem object = data.getObject(i, AttendanceItem.class);
      list.add(object);
      Log.i(AppConfig.TAG, object.toString());
    }
    //初始化适配器
    myAdapter = new MyRecyclerViewAdapter(list);

    //设置添加或删除item时的动画，这里使用默认动画
    stuAty_checkAtyRv.setItemAnimator(new DefaultItemAnimator());

    //设置适配器
    stuAty_checkAtyRv.setAdapter(myAdapter);

    //添加横线
    stuAty_checkAtyRv.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
    Log.i(AppConfig.TAG, data.toString());
    XToast.success(getContext(), "数据获取成功").show();

    myAdapter.setOnItemClickListener((view, position, data1) ->{
      Intent intent = new Intent(getContext(), CheckSpecificInfoAty.class);

      startActivity(intent);
    });
  }

  @Override
  public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
    XToast.error(getContext(), "请求网络数据失败：" + statusCode).show();
    finish();
  }
});
```



### 6、学生端睡觉检测提醒

学生端睡觉检测提醒是非常重要的一个功能，因为考勤并不能代表一个人的专注与否，只要有睡觉的现象发生我们就应该给与提醒，及时提醒有助于提升学生的学习状况

![](https://s2.ax1x.com/2019/05/21/EzR7rt.png)

App在后台开启服务线程，采用每间隔5s的机制不断访问服务器端消息队列，只要检测到有服务器发来的消息便会发起手机震动提醒，于是便起到了提醒的作用！

```java
//开启检测服务
Timer timer = new Timer();
Vibrator mVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
new Thread(()-> timer.schedule(new TimerTask() {
  @Override
  public void run() {
    Map<String, String> map = new HashMap<>();
    String stu_id = first.getString("stu_id", "");
    assert stu_id != null;
    map.put("id", stu_id);
    String post = GetPostUrl.post(AppConfig.nowSmg, map);
    JSONObject object = JSONObject.parseObject(post);
    Integer status = object.getInteger("status");
    String msg = object.getString("msg");
    if(status == 0){
      Log.i(AppConfig.TAG, "msg:" + msg);
    }else if(status == 1){
      runOnUiThread(()-> XToast.info(getContext(), "检测到你正在睡觉！").show());
      mVibrator.vibrate(new long[]{1000, 10000, 2000, 10000}, -1);
    }
  }
}, new Date(), 5000)).start();
```



### 7、教师端课堂考勤



## 四、Github开源地址

[《智慧教室》](https://github.com/xpuintelligence/Intelligence_classroom/tree/android)

![](https://s2.ax1x.com/2019/05/21/EzfXcj.png)









