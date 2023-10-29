import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import AddEventView from '@/views/AddEventView.vue'
import EventDetailsView from '@/views/EventDetailsView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
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
