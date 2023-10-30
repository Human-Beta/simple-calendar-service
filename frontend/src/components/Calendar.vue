<template>
  <div class="container">
    <div class="header">
      <div class="btns">
        <div @click="decrementMonth">&lt;</div>
        <span @click="setCurrentMonth">now</span>
        <div @click="incrementMonth">></div>
      </div>
      <h1 class="month">
        {{ currentDate.toLocaleString('default', { month: 'long', year: 'numeric' }) }}
      </h1>
    </div>
    <div class="calendar">
      <Day v-for="day in days" :key="day.date.getDate()" :day="day" />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import Day from '@/components/Day.vue'
import type { CalendarDay } from '@/types'

export default defineComponent({
  name: 'EventCalendar',
  components: { Day },
  data() {
    // TODOM: remove
    const today = new Date()

    return {
      currentDate: new Date(),
      events: [
        {
          id: 1,
          title: 'title-1',
          startDate: new Date(2023, today.getMonth(), today.getDate(), 13),
          endDate: new Date(2023, today.getMonth(), today.getDate(), 15)
        },
        {
          id: 2,
          title: 'title-2',
          startDate: new Date(2023, today.getMonth(), today.getDate(), 10),
          endDate: new Date(2023, today.getMonth(), today.getDate(), 12)
        },
        {
          id: 3,
          title: 'title-2',
          startDate: new Date(2023, today.getMonth(), today.getDate() + 1, 10),
          endDate: new Date(2023, today.getMonth(), today.getDate() + 1, 12)
        }
      ]
    }
  },
  computed: {
    days() {
      let date = new Date(this.currentDate.getFullYear(), this.currentDate.getMonth(), 1)
      const days: CalendarDay[] = []
      while (date.getMonth() === this.currentDate.getMonth()) {
        const dayDate = new Date(date)
        const day = {
          date: dayDate,
          events: this.getEventsForDate(dayDate)
        }
        days.push(day)
        date.setDate(date.getDate() + 1)
      }
      return days
    }
  },
  methods: {
    decrementMonth() {
      this.currentDate = new Date(this.currentDate.getFullYear(), this.currentDate.getMonth() - 1)
    },
    incrementMonth() {
      this.currentDate = new Date(this.currentDate.getFullYear(), this.currentDate.getMonth() + 1)
    },
    setCurrentMonth() {
      this.currentDate = new Date()
    },
    getEventsForDate(date: Date) {
      return this.events
        .filter((event) => event.startDate.getDate() === date.getDate())
        .sort((e1, e2) => e1.startDate.valueOf() - e2.startDate.valueOf())
    }
  }
})
</script>

<style scoped>
.container {
  margin: 0 auto;
  width: 1200px;
}

.header {
  display: flex;
  align-items: center;
}

.btns {
  font-size: 25px;
  margin-right: 10px;
}

.btns div {
  border-radius: 30px;
  height: 40px;
  width: 40px;
  cursor: pointer;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  user-select: none;
}

.btns span {
  border-radius: 25px;
  padding: 8px;
  cursor: pointer;
}

.btns span:active,
.btns div:active {
  background-color: rgb(230, 230, 230);
}

.month {
  display: inline;
}

.calendar {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  text-align: center;
}
</style>
