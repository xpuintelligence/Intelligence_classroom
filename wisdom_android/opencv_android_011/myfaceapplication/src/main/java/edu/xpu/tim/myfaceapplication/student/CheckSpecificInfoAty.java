package edu.xpu.tim.myfaceapplication.student;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.widget.LinearLayout;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.xuexiang.xui.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import edu.xpu.tim.myfaceapplication.R;

public class CheckSpecificInfoAty extends AppCompatActivity {

    protected final String[] parties = new String[]{
            "抬头率得分率", "出勤得分率", "XX得分率", "BB得分率", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };
    private PieChart stu_half_chart;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_specific_info_aty);
        stu_half_chart = findViewById(R.id.stu_half_chart);
        initViews();
    }


    protected void initViews() {
        moveOffScreen();
        initChartStyle();
        initChartLabel();
        setChartData(4, 100);
        stu_half_chart.animateY(1400, Easing.EaseInOutQuad);
    }


    /**
     * 初始化图表的样式
     */
    protected void initChartStyle() {
        stu_half_chart.setBackgroundColor(Color.WHITE);
        //使用百分百显示
        stu_half_chart.setUsePercentValues(true);
        stu_half_chart.getDescription().setEnabled(false);

        //设置图标中心文字
        stu_half_chart.setCenterText(generateCenterSpannableText());
        stu_half_chart.setDrawCenterText(true);
        stu_half_chart.setCenterTextOffset(0, -20);
        //设置图标中心空白，空心
        stu_half_chart.setDrawHoleEnabled(true);
        stu_half_chart.setHoleRadius(58f);
        stu_half_chart.setHoleColor(Color.WHITE);
        //设置透明弧的样式
        stu_half_chart.setTransparentCircleColor(Color.WHITE);
        stu_half_chart.setTransparentCircleAlpha(110);
        stu_half_chart.setTransparentCircleRadius(61f);

        //设置不可以旋转
        stu_half_chart.setRotationEnabled(false);
        stu_half_chart.setHighlightPerTapEnabled(true);

        //====设置半圆弧===//
        stu_half_chart.setMaxAngle(180f);
        stu_half_chart.setRotationAngle(180f);
    }

    protected void initChartLabel() {
        Legend l = stu_half_chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        stu_half_chart.setEntryLabelColor(Color.WHITE);
        stu_half_chart.setEntryLabelTextSize(12f);
    }

    /**
     * 设置图表数据
     * @param count 一组数据的数量
     */
    protected void setChartData(int count, float range) {
        List<PieEntry> values = new ArrayList<>();
        //设置数据源
        for (int i = 0; i < count; i++) {
            values.add(new PieEntry((float) ((Math.random() * range) + range / 5), parties[i % parties.length]));
        }
        PieDataSet dataSet = new PieDataSet(values, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        stu_half_chart.setData(data);

        stu_half_chart.invalidate();
    }

    /**
     * 生成饼图中间的文字
     */
    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString("数据分析结果\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }
    /**
     * 进行偏移，将宁外一半的圆弧隐藏掉
     */
    private void moveOffScreen() {
        int height = DensityUtils.getDisplayMetrics().heightPixels;

        /* percent to move */
        int offset = (int)(height * 0.65);

        LinearLayout.LayoutParams rlParams = (LinearLayout.LayoutParams) stu_half_chart.getLayoutParams();
        rlParams.setMargins(0, 0, 0, -offset);
        stu_half_chart.setLayoutParams(rlParams);
    }
}
