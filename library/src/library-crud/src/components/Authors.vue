<template>
  <div id="authors">
    <table class="table">
      <tr>
        <th scope="col">Name</th>
        <th scope="col">Last name</th>
        <th scope="col">Actions</th>
      </tr>
      <tbody>
      <tr v-for="author in authors" :key="author.id">
        <td>{{author.authorName}}</td>
        <td>{{author.authorLastName}}</td>
        <td>
          <button type="button" class="btn btn-danger" @click="deleteAuthor(author.id)">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
              <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
              <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
            </svg>
          </button>

          <button class="btn btn-warning" style="margin-left: 20px;">
            <router-link :to="'/authors/' + author.id" >Edit</router-link>
          </button>

        </td>
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
      authors: [],
      author: null,
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

    deleteAuthor(id) {
      if (confirm('Are you sure you want to delete this author?')) {
        AuthorDataService.delete(id)
            .then(response => {
              console.log(response.data);
              this.$router.push({ name: "authors" });
            })
            .catch(e => {
              console.log(e);
            });
        location.reload();
      } else {
        //do nothing
      }
    },

    updateAuthor() {
      AuthorDataService.update(this.currentTutorial.id, this.currentTutorial)
          .then(response => {
            console.log(response.data);
            this.message = 'Updated successfully!';
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