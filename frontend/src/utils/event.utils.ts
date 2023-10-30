import type { CalendarEvent, ResponseEvent } from '@/types'
import { parseDate } from "@/utils/date.utils";

export const responseEventToCalendarEvent = (resEvent: ResponseEvent): CalendarEvent => {
  return {
    ...resEvent,
    startDate: parseDate(resEvent.startDate),
    endDate: parseDate(resEvent.endDate)
  }
}
