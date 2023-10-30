<template>
  <div v-if="loading">
    <div class="loader">Loading...</div>
  </div>
  <div v-else>
    <div class="container">
      <v-form @submit="submitForm">
        <div>
          <label for="title">Title:</label>
          <v-field id="title" name="title" rules="required" v-model="event.title" />
          <error-message name="title" class="error"></error-message>
        </div>
        <div>
          <label for="description">Description:</label>
          <textarea id="description" v-model="event.description"></textarea>
        </div>
        <div>
          <label for="startDate">Start date:</label>
          <v-field
            id="startDate"
            name="startDate"
            type="datetime-local"
            v-model="formattedStartDate"
            :rules="{ required: true, before: [event.endDate, 'EndDate'] }"
          />
          <error-message name="startDate" class="error"></error-message>
        </div>
        <div>
          <label for="endDate">End date:</label>
          <v-field
            id="endDate"
            name="endDate"
            type="datetime-local"
            v-model="formattedEndDate"
            :rules="{ required: true, after: [event.startDate, 'StartDate'] }"
          />
          <error-message name="endDate" class="error"></error-message>
        </div>
        <div>
          <label for="location">Location:</label>
          <input id="location" v-model="event.location" />
        </div>

        <button type="submit">Save</button>
        <button type="button" @click="$router.push('/')">Cancel</button>
      </v-form>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { defineRule, Form, Field, ErrorMessage } from 'vee-validate'

defineRule('required', (value, params, ctx) => {
  return value ? true : `${ctx.name} is required`
})

defineRule('before', (value: string, params: [Date, string], ctx) => {
  const date = params[0]
  const dateName = params[1]
  return new Date(value).valueOf() <= date.valueOf()
    ? true
    : `${ctx.name} should be before ${dateName}`
})

defineRule('after', (value: string, params: [Date, string], ctx) => {
  const date = params[0]
  const dateName = params[1]
  return new Date(value).valueOf() >= date.valueOf()
    ? true
    : `${ctx.name} should be after ${dateName}`
})

export default defineComponent({
  components: {
    VForm: Form,
    VField: Field,
    ErrorMessage: ErrorMessage
  },
  data() {
    return {
      loading: true,
      event: {
        title: 'Some title',
        description: 'Some description',
        startDate: new Date(2023, 9, 30, 13),
        endDate: new Date(2023, 9, 30, 15),
        location: 'Some location'
      }
    }
  },
  computed: {
    formattedStartDate: {
      get() {
        return this.formatDate(this.event.startDate)
      },
      set(value: string) {
        this.event.startDate = new Date(value)
      }
    },
    formattedEndDate: {
      get() {
        return this.formatDate(this.event.endDate)
      },
      set(value: string) {
        this.event.endDate = new Date(value)
      }
    }
  },
  created() {
    this.fetchEvent()
  },
  methods: {
    async fetchEvent() {
      //   TODOM: fetchEvent
      setTimeout(() => (this.loading = false), 200)
    },
    submitForm() {
      //   TODOM: submit
      console.log('submit')
    },
    formatDate(date: Date) {
      const yyyy = date.getFullYear()
      const mm = (date.getMonth() + 1).toString().padStart(2, '0')
      const dd = date.getDate().toString().padStart(2, '0')
      const hh = date.getHours().toString().padStart(2, '0')
      const min = date.getMinutes().toString().padStart(2, '0')

      return `${yyyy}-${mm}-${dd}T${hh}:${min}`
    }
  }
})
</script>

<style scoped>
.loader,
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.container {
  padding: 1em;
  box-sizing: border-box;
}

form {
  flex: 1;
  max-width: 600px;
  padding: 1em;
  border: 1px solid #999;
  border-radius: 0.5em;
}

form > div {
  margin-bottom: 1em;
}

label {
  font-weight: bold;
}

input,
textarea {
  padding: 0.8em;
  width: 100%;
}

button {
  width: 100%;
  padding: 1em;
  border: none;
  border-radius: 0.5em;
  cursor: pointer;
  margin-bottom: 1em;
}

button[type="submit"] {
  background-color: blue;
  color: white;
}

button[type="submit"]:hover {
  background-color: darkblue;
}
button[type="button"]:hover {
  background-color: #c5c5c5;
}

.error {
  display: inline-block;
  color: red;
}

.error::first-letter {
  text-transform: uppercase;
}
</style>
