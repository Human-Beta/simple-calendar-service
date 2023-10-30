<template>
  <div v-if="loading">
    <div class="loader">
      Loading...
    </div>
  </div>
  <div v-else>
    <div class="container">
      <form @submit.prevent="submitForm">
        <div>
          <label for="title">Title:</label>
          <input id="title" v-model="event.title" required/>
        </div>
        <div>
          <label for="description">Description:</label>
          <textarea id="description" v-model="event.description"></textarea>
        </div>
        <div>
          <label for="startDate">Start date:</label>
          <input type="datetime-local" id="startDate" v-model="formattedStartDate" required/>
        </div>
        <div>
          <label for="endDate">End date:</label>
          <input type="datetime-local" id="endDate" v-model="formattedEndDate" required/>
        </div>
        <div>
          <label for="location">Location:</label>
          <input id="location" v-model="event.location"/>
        </div>

        <button type="submit">Save</button>
      </form>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent} from 'vue'

export default defineComponent({
  data() {
    return {
      loading: true,
      event: {
        title: "Some title",
        description: "Some description",
        startDate: new Date(2023, 9, 30, 13),
        endDate: new Date(2023, 9, 30, 15),
        location: "Some location",
      }
    };
  },
  computed: {
    formattedStartDate: {
      get() {
        return this.formatDate(this.event.startDate);
      },
      set(value: string) {
        this.event.startDate = new Date(value);
      }
    },
    formattedEndDate: {
      get() {
        return this.formatDate(this.event.endDate);
      },
      set(value: string) {
        this.event.endDate = new Date(value);
      }
    }
  },
  created() {
    this.fetchEvent();
  },
  methods: {
    async fetchEvent() {
      //   TODOM: fetchEvent
      setTimeout(() => this.loading = false, 200)
    },
    submitForm() {
    //   TODOM: submit
      console.log("submit")
    },
    formatDate(date: Date) {
      const yyyy = date.getFullYear()
      const mm = (date.getMonth() + 1).toString().padStart(2, '0')
      const dd = date.getDate().toString().padStart(2, '0')
      const hh = date.getHours().toString().padStart(2, '0')
      const min = date.getMinutes().toString().padStart(2, '0')

      return `${yyyy}-${mm}-${dd}T${hh}:${min}`;
    }
  }
})
</script>

<style scoped>
.loader, .container {
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

input, textarea {
  padding: 0.8em;
  width: 100%;
}

button {
  background-color: blue;
  color: white;
  width: 100%;
  padding: 1em;
  border: none;
  border-radius: 0.5em;
  cursor: pointer;
}

button:hover {
  background-color: darkblue;
}
</style>