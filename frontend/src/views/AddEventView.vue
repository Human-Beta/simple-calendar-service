<template>
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

      <button type="submit">Create Event</button>
      <button type="button" @click="$router.push('/')">Go back</button>
    </v-form>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { Form, Field, ErrorMessage } from 'vee-validate'
import { formatDate, parseDate } from '@/utils/date.utils'
import type { Moment } from 'moment/moment'

export default defineComponent({
  components: {
    VForm: Form,
    VField: Field,
    ErrorMessage: ErrorMessage
  },
  data() {
    return {
      event: {
        title: '',
        description: '',
        startDate: null as Moment | null,
        endDate: null as Moment | null,
        location: ''
      }
    }
  },
  computed: {
    formattedStartDate: {
      get() {
        return formatDate(this.event.startDate)
      },
      set(value: string) {
        this.event.startDate = parseDate(value)
      }
    },
    formattedEndDate: {
      get() {
        return formatDate(this.event.endDate)
      },
      set(value: string) {
        this.event.endDate = parseDate(value)
      }
    }
  },
  methods: {
    submitForm() {
      this.$http
        .post('/events', this.event)
        .then(() => {
          this.$router.push('/')
        })
        .catch((reason) => {
          alert(reason)
        })
    }
  }
})
</script>

<style scoped>
@import '@/assets/event.css';
</style>
