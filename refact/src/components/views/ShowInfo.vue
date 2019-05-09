<template>
  <div>
    <!--{{userData}}-->
    <!--<hr>-->
    你好~<br>
    学号：{{userData.id}}<br>
    姓名：{{userData.name}}<br>
    班级编号：{{userData.classId}}<br>
  </div>
</template>

<script>
  export default {
    name: "test",
    data() {
      return {
        userData: {
          classId: '',
          collegeId: '',
          email: '',
          id: '',
          identityId: '',
          name: '',
          password: '',
          phoneCode: '',
          picture: '',
          professionId: '',
          sex: '',
          wexinId: '',
        },
      }
    },
    mounted() {
      this.userData = JSON.parse(localStorage.getItem('userData') || '[]');
      // console.log(this.userData);
    },
  }
</script>

<style scoped>

</style>
