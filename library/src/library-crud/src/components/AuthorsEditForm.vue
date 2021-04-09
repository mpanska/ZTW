<template>
  <form  @submit.prevent="updateAuthor">
    <div class="form-group">
      <label for="authorName">Name</label>
      <input
          id="authorName"
          v-model="author.authorName"
          type="text"
          class="form-control"
          :class="{'is-invalid': submitting && invalidName }"
          @focus="clearStatus"
          @keypress="clearStatus"
      />
      <label for="authorLastName" id="mt1">Last name</label>
      <input
          id="authorLastName"
          v-model="author.authorLastName"
          type="text"
          :class="{ 'is-invalid': submitting && invalidLastName }"
          @focus="clearStatus"
          class="form-control"
      />

      <p v-if="error && submitting" class="error-message">
        Please, fill all of the fields.
      </p>
      <p v-if="success" class="success-message">
        Updated.
      </p>

      <button class="btn btn-success">Update author</button>
    </div>
  </form>
</template>



<script>
import AuthorDataService from "@/services/AuthorDataService";

export default {
  name: 'authors-edit-form',
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
      author: null
    }
  },
  methods: {

    getAuthor(id) {
      AuthorDataService.get(id)
          .then(response => {
            this.author = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },

    updateAuthor() {
      this.submitting = true
      this.clearStatus()

      //check form fields
      if (this.invalidName || this.invalidLastName) {
        this.error = true
        return
      }

      AuthorDataService.update(this.author.id, this.author)
          .then(response => {
            console.log(response.data);
            this.success = true
          })
          .catch(e => {
            console.log(e);
          });

      this.clearStatus()
    },


    clearStatus() {
      this.success = false
      this.error = false
    },
  },

  mounted() {
    this.getAuthor(this.$route.params.id);
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
