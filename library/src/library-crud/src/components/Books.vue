<template>
  <div id="books">
    <table>
      <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Pages</th>
        <th>Borrowed</th>
      </tr>
      <tbody>
      <tr v-for="book in books" :key="book.id">
        <td>{{ book.title}}</td>
        <td>{{ book.author.authorName}} {{ book.author.authorLastName}}</td>
        <td>{{ book.pages }}</td>
        <td>{{ book.borrowed }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import BookDataService from '../services/BookDataServeice';

export default {
  name: 'books',
  components: {
  },
  data() {
    return {
      books: []
    }
  },


  methods: {
    async getAllBooks() {
      BookDataService.getAll()
          .then(response => {
            this.books = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },

  },
  mounted() {
    this.getAllBooks()
  },
}
</script>

<style scoped>
</style>