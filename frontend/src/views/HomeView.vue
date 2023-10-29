<script setup lang="ts">
import Calendar from '@/components/Calendar.vue'
</script>

<template>
  <h1>Hello world!</h1>
  <RouterLink to="/add-event">Add an event</RouterLink>
  <br />
  <RouterLink to="/event/1">Show details about an event</RouterLink>

  <Calendar />
</template>

<style scoped></style>
