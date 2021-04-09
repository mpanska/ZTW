<template>
  <form @submit.prevent="saveAuthor">
    <label>Name</label>
    <input
        v-model="author.authorName"
        type="text"
        :class="{ 'has-error': submitting && invalidName }"

        @focus="clearStatus"
        @keypress="clearStatus"
    />
    <label>Last name</label>
    <input
        v-model="author.authorLastName"
        type="text"
        :class="{ 'has-error': submitting && invalidLastName }"
        @focus="clearStatus"
    />

    <p v-if="error && submitting" class="error-message">
      Please, fill all of the fields.
    </p>
    <p v-if="success" class="success-message">
      Successfully saved.
    </p>

    <button>Add author</button>
  </form>
</template>



<script>
import AuthorDataService from "@/services/AuthorDataService";

export default {
  name: 'authors-form',
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
      author: {
        id: null,
        authorName: '',
        authorLastName: '',
      },
    }
  },
  methods: {
    saveAuthor() {
      this.submitting = true
      this.clearStatus()

      //check form fields
      if (this.invalidName || this.invalidLastName) {
        this.error = true
        return
      }

      const data = {
        authorName: this.author.authorName,
        authorLastName: this.author.authorLastName
      };

      AuthorDataService.create(data)
          .then(response => {
            this.author.id = response.data.id;
            console.log(response.data);
            this.success = true;
          })
          .catch(e => {
            console.log(e);
          });

      this.error = false
      this.success = true
      this.submitting = false

      this.author = {
        authorName: '',
        authorLastName: '',
      }
    },


    clearStatus() {
      this.success = false
      this.error = false
    },
  },

  computed: {
    invalidName() {
      return this.author.authorName === ''
    },
    invalidLastName() {
      return this.author.authorLastName === ''
    },
  },
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
</style>
