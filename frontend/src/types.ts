import type { Moment } from "moment";

export interface CalendarDay {
  date: Moment
  events: CalendarEvent[]
}

export interface CalendarEvent {
  id: number
  title: string
  description?: string
  startDate: Moment
  endDate: Moment
  location?: string
}

export interface ResponseEvent {
  id: number
  title: string
  description?: string
  startDate: string
  endDate: string
  location?: string
}
