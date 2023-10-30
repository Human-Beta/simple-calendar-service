import './assets/main.css'

import { createApp } from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import App from './App.vue'
import router from './router'
import { defineRule } from 'vee-validate'

const app = createApp(App)

app.use(router)

defineRule('required', (value, _, ctx) => {
  return value ? true : `${ctx.name} is required`
})

defineRule('before', (value: string, params: [Date, string], ctx) => {
  const targetDate = new Date(value)
  const date = params[0]
  const dateName = params[1]

  if (!date || isNaN(date.getTime())) {
    return true
  }

  return targetDate.valueOf() <= date.valueOf() ? true : `${ctx.name} should be before ${dateName}`
})

defineRule('after', (value: string, params: [Date, string], ctx) => {
  const targetDate = new Date(value)
  const date = params[0]
  const dateName = params[1]

  if (!date || isNaN(date.getTime())) {
    return true
  }

  return targetDate.valueOf() >= date.valueOf() ? true : `${ctx.name} should be after ${dateName}`
})

axios.defaults.baseURL = 'http://localhost:8080'
app.use(VueAxios, axios).mount('#app')
