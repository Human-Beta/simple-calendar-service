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
