<template>
  <form  @submit.prevent="saveBook">
    <div class="form-group">
      <label for="title">Name</label>
      <input
          id="title"
          v-model="book.title"
          type="text"
          class="form-control"
          :class="{'is-invalid': submitting && invalidTitle }"
          @focus="clearStatus"
          @keypress="clearStatus"
      />

      <label for="author">Author</label>
      <select v-model="author" 
              :class="{ 'is-invalid': submitting && invalidAuthor }"
              @focus="clearStatus"
              class="form-control">
        <option disabled value="" selected="selected">Please select one</option>
        <option v-for="author in authors" :key="author.id" v-bind:value="author.id">
           {{author.authorName}} {{author.authorLastName}}
        </option>
      </select>

      <label for="pages" id="mt1">Pages</label>
      <input
          id="pages"
          v-model="book.pages"
          type="number"
          min="1"
          :class="{ 'is-invalid': submitting && invalidPages }"
          @focus="clearStatus"
          class="form-control"
      />

      <p v-if="error && submitting" class="error-message">
        Please, fill all of the fields.
      </p>
      <p v-if="success" class="success-message">
        Successfully saved.
      </p>

      <button class="btn btn-success" >Add book</button>
    </div>
  </form>
</template>


<script>
import BookDataServeice from "@/services/BookDataServeice";
import AuthorDataService from "@/services/AuthorDataService";

export default {
  name: 'books-form',
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
      book: {
        id: null,
        title: '',
        pages: '',
        author: {
            id: null
        }
      },
      authors: [],
      author: null
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

    saveBook() {
      this.submitting = true
      this.clearStatus()

      //check form fields
      if (this.invalidTitle || this.invalidPages || this.invalidAuthor) {
        this.error = true
        return
      }

      const data = {
        title: this.book.title,
        pages: this.book.pages,
        author: {id: this.author}
      };

      BookDataServeice.create(data)
          .then(response => {
            this.book.id = response.data.id;
            console.log(response.data);
            this.success = true;
          })
          .catch(e => {
            console.log(e);
          });

      this.error = false
      this.success = true
      this.submitting = false

      this.book = {
        title: '',
        pages: '',
        author: {
          id: null
        }
      }
    },


    clearStatus() {
      this.success = false
      this.error = false
    },
  },

  computed: {
    invalidTitle() {
      return this.book.title === ''
    },
    invalidPages() {
      return this.book.pages < ''
    },
    invalidAuthor() {
      return this.author === null
    }
  },

  mounted() {
    this.getAuthors()
  }
}
</script>

<style scoped>
  form {
    margin-bottom: 2rem;
  }

  [class*='-message'] {
    font-weight: 500;
  }
  .error-message {
    color: #d33c40;
  }
  .success-message {
    color: #32a95d;
  }

  .btn{
    margin-top: 20px;
  }

  .form-group{
    font-weight: bolder;
    font-size: 20px;
  }

  #authorName, #mt1{
    margin-top: 10px;
  }

  #authorLastName{
    margin-top: 6px;
  }

</style>
