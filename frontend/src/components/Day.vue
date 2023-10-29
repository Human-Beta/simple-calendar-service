<template>
  <div class="day">
    {{ day.date.getDate() }}
    <span v-for="event in day.events" :key="event.id">
      <router-link :to="`/event/${event.id}`">{{ event.title }}</router-link>
    </span>
  </div>
</template>

<script lang="ts">
import {defineComponent, PropType} from 'vue'
import type { CalendarDay } from "@/types";

export default defineComponent({
  name: 'EventDay',
  props: {
    day: {
      type: Object as PropType<CalendarDay>,
      required: true
    }
  }
})
</script>

<style scoped>
.day {
  border: 1px solid #ccc;
  padding: 10px;
}
</style>
