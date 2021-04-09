<template>
  <div id="authors">
    <table>
      <tr>
        <th>Name</th>
        <th>Last name</th>
      </tr>
      <tbody>
      <tr v-for="author in authors" :key="author.id">
        <td>{{ author.authorName}}</td>
        <td>{{ author.authorLastName}}</td>
      </tr>
      </tbody>
    </table>
    <button class="btn btn-outline-primary">
      <router-link to="/addAuthor" class="nav-link">Add author</router-link>
    </button>

  </div>
</template>


<script>
  import AuthorDataService from "@/services/AuthorDataService";

  export default {
  name: 'authors',
  components: {
  },

  data() {
    return {
      authors: []
    }
  },
  methods: {

    async getAuthors() {
      AuthorDataService.getAll()
          .then(response => {
            this.authors = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },

  },

  mounted() {
    this.getAuthors()
  },
}
</script>

<style scoped>
</style>