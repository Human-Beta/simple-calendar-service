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
      <router-link to="/add-event">Add event</router-link>
    </div>
    <div class="calendar">
      <Day v-for="day in days" :key="day.date.getDate()" :day="day" />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import Day from '@/components/Day.vue'
import type { CalendarDay, CalendarEvent, ResponseEvent } from '@/types'
import type { AxiosResponse } from 'axios'

export default defineComponent({
  name: 'EventCalendar',
  components: { Day },
  data() {
    return {
      currentDate: new Date(),
      events: [] as CalendarEvent[]
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
  created() {
    this.$http.get('/events').then((res: AxiosResponse<ResponseEvent[]>) => {
      this.events = res.data.map((resEvent) => ({
        ...resEvent,
        startDate: new Date(resEvent.startDate),
        endDate: new Date(resEvent.endDate)
      }))
    })
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
        .filter((event) => event.startDate.getMonth() === date.getMonth())
        .filter((event) => event.startDate.getDate() === date.getDate())
        .sort((e1, e2) => e1.startDate.valueOf() - e2.startDate.valueOf())
    }
  }
})
</script>

<style scoped>
.container {
  margin: 0 auto;
  width: 1400px;
}

.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.header a,
.header a:active {
  display: inline-block;
  padding: 5px 12px;
  color: white;
  background-color: #007bff;
  border: none;
  border-radius: 20px;
  text-decoration: none;
  font-weight: bold;
  text-align: center;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.header a:hover {
  background-color: #0056b3;
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
