<template>
  <div class="day">
    <strong>{{ day.date.get('date') }}</strong>
    <div class="events">
      <div
        v-for="event in day.events"
        :key="event.id"
        :class="['event', getColorClass(event.id)]"
        @click="goToEventDetailsPage(event.id)"
      >
        {{ event.title }}
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
    },
    getColorClass(num: number) {
      const colors = [
        'red',
        'blue',
        'green',
        'yellow',
        'purple',
        'orange',
        'hotpink',
        'brown',
        'teal',
        'grey',
        'cyan'
      ]

      return colors[num % colors.length]
    }
  }
})
</script>

<style scoped>
.day {
  border: 1px solid #ccc;
  width: 200px;
  height: 160px;
  padding: 10px 0;
}

.events {
  overflow-y: auto;
  height: 110px;
}

.event {
  margin-bottom: 2px;
  background-color: rgb(66, 133, 244);
  color: white;
  text-align: start;
  padding: 0 5px;
  font-weight: 500;
  cursor: pointer;
}

.red {
  background-color: red;
}

.blue {
  background-color: blue;
}

.green {
  background-color: green;
}

.yellow {
  background-color: yellow;
}

.purple {
  background-color: purple;
}

.orange {
  background-color: orange;
}

.pink {
  background-color: hotpink;
}

.brown {
  background-color: brown;
}

.teal {
  background-color: teal;
}

.grey {
  background-color: grey;
}

.cyan {
  color: cyan;
}
</style>
