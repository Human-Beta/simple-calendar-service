<template>
  <div class="root">
    <h2>{{ now.toLocaleString('default', { month: 'long' }) }}</h2>
    <div class="calendar">
      <Day v-for="day in days" :key="day.date" :day="day"/>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import Day from '@/components/Day.vue';
import type { CalendarDay } from "@/types";

export default defineComponent({
  name: 'EventCalendar',
  components: { Day },
  data() {
    return {
      now: new Date()
    }
  },
  computed: {
    days() {
      const month = this.now.getMonth()
      let date = new Date(this.now.getFullYear(), month, 1)
      const days: CalendarDay[] = []
      while (date.getMonth() === month) {
        let day = {
          date: new Date(date),
          events:
            date.getDate() == 29
              ? [
                  {
                    id: 'id-1',
                    title: 'title-1'
                  },
                  {
                    id: 'id-2',
                    title: 'title-2'
                  }
                ]
              : []
        }
        days.push(day)
        date.setDate(date.getDate() + 1)
      }
      return days
    }
  }
})
</script>

<style scoped>
.root {
  margin: 0 auto;
  width: 1200px;
}

.calendar {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  text-align: center;
}
</style>
