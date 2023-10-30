<template>
  <div class="day">
    <strong>{{ day.date.getDate() }}</strong>
    <div class="events">
      <div
        v-for="event in day.events"
        :key="event.id"
        class="event"
        @click="goToEventDetailsPage(event.id)"
      >
        {{ event.startDate.toLocaleString('default', { hour: 'numeric' }).replace(' ', '') }} {{ event.title }}
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import type { PropType } from 'vue'
import type { CalendarDay } from '@/types'

export default defineComponent({
  name: 'EventDay',
  props: {
    day: {
      type: Object as PropType<CalendarDay>,
      required: true
    }
  },
  methods: {
    goToEventDetailsPage(eventId: number) {
      this.$router.push(`/event/${eventId}`)
    }
  }
})
</script>

<style scoped>
.day {
  border: 1px solid #ccc;
  width: 200px;
  height: 160px;
  padding: 10px;
}

.events {
  overflow-y: auto;
  height: 110px;
}

.event {
  margin-bottom: 2px;
  border-radius: 4px;
  background-color: rgb(66, 133, 244);
  color: white;
  text-align: start;
  padding: 0 5px;
  font-weight: 500;
  cursor: pointer;
}
</style>
