<template>

<v-data-table
    :headers="headers"
    :items="mypage"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'Mypage',
    props: {
      value: Object,
      editMode: Boolean,
      isNew: Boolean
    },
    data: () => ({
        headers: [
            { text: "bookId", value: "bookId" },
            { text: "vaccineId", value: "vaccineId" },
            { text: "vcName", value: "vcName" },
            { text: "userId", value: "userId" },
            { text: "status", value: "status" },
        ],
        mypage : [],
    }),
    async created() {
      var temp = await axios.get(axios.backend + '/mypages')

      this.mypage = temp.data._embedded.mypages;

    },
    methods: {
    }
  }
</script>

