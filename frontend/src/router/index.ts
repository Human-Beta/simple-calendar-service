import { createRouter, createWebHistory } from 'vue-router'
// TODOM: replace "../..." to "@/..." in the whole project
import CalendarView from '@/views/CalendarView.vue'
import AddEventView from '@/views/AddEventView.vue'
import EventDetailsView from '@/views/EventDetailsView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'calendar',
      component: CalendarView
    },
    {
      path: '/add-event',
      name: 'addEvent',
      component: AddEventView
    },
    {
      path: '/event/:id',
      name: 'eventDetails',
      component: EventDetailsView
    }
  ]
})

export default router
