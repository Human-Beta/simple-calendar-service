<template>
  <div class="root">
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
    return {
      currentDate: new Date()
    }
  },
  computed: {
    days() {
      let date = new Date(this.currentDate.getFullYear(), this.currentDate.getMonth(), 1)
      const days: CalendarDay[] = []
      while (date.getMonth() === this.currentDate.getMonth()) {
        let day = {
          date: new Date(date),
          events:
            this.currentDate.getMonth() === 9 && date.getDate() === 29
              ? [
                  {
                    id: '1',
                    title: 'title-1'
                  },
                  {
                    id: '2',
                    title: 'title-2'
                  }
                ]
              : this.currentDate.getMonth() === 9 && date.getDate() === 30
              ? [
                  {
                    id: '3',
                    title: 'title-3'
                  }
                ]
              : []
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
    }
  }
})
</script>

<style scoped>
.root {
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
