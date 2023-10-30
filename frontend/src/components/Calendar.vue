<template>
  <div class="container">
    <div class="header">
      <div class="btns">
        <div @click="decrementMonth">&lt;</div>
        <span @click="setCurrentMonth">now</span>
        <div @click="incrementMonth">></div>
      </div>
      <h1 class="month">
        {{ currentDate.format('MMMM') }}
      </h1>
      <router-link to="/add-event">Add event</router-link>
    </div>
    <div class="calendar">
      <Day v-for="day in days" :key="day.date.valueOf()" :day="day" />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import type { AxiosResponse } from 'axios'
import Day from '@/components/Day.vue'
import { responseEventToCalendarEvent } from '@/utils/event.utils'
import type { CalendarDay, CalendarEvent, ResponseEvent } from '@/types'
import type { Moment } from 'moment'
import { copy, now } from '@/utils/date.utils'

export default defineComponent({
  name: 'EventCalendar',
  components: { Day },
  data() {
    return {
      currentDate: now(),
      events: [] as CalendarEvent[]
    }
  },
  computed: {
    days() {
      let date = copy(this.currentDate).set('date', 1)
      const days: CalendarDay[] = []
      while (date.get('month') === this.currentDate.get('month')) {
        const dayDate = copy(date)
        const day = {
          date: dayDate,
          events: this.getEventsForDate(date)
        }
        days.push(day)
        date.set('date', date.get('date') + 1)
      }
      return days
    }
  },
  created() {
    this.$http.get('/events').then((res: AxiosResponse<ResponseEvent[]>) => {
      this.events = res.data.map(responseEventToCalendarEvent)
    })
  },
  methods: {
    decrementMonth() {
      this.currentDate = copy(this.currentDate).set('month', this.currentDate.get('month') - 1)
    },
    incrementMonth() {
      this.currentDate = copy(this.currentDate).set('month', this.currentDate.get('month') + 1)
    },
    setCurrentMonth() {
      this.currentDate = now()
    },
    getEventsForDate(date: Moment) {
      return this.events
        .filter((event) => event.startDate.get('month') === date.get('month'))
        .filter(
          (event) =>
            event.startDate.get('date') <= date.get('date') &&
            date.get('date') <= event.endDate.get('date')
        )
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
