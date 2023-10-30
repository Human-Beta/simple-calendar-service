import type { CalendarEvent, ResponseEvent } from '@/types'

export const responseEventToCalendarEvent = (resEvent: ResponseEvent): CalendarEvent => ({
  ...resEvent,
  startDate: new Date(resEvent.startDate),
  endDate: new Date(resEvent.endDate)
})
