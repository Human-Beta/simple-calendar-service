export interface CalendarDay {
  date: Date
  events: CalendarEvent[]
}

export interface CalendarEvent {
  id: number
  title: string
  description?: string
  startDate: Date
  endDate: Date
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
