export const PRICES = {
  CREW: 9.99,
  INSAURANCE: 19.99
}

export const SCHEDULES = {
  FIRST: 'Primer turno: 08:00h - 12:00h',
  SECOND: 'Segundo turno: 13:00h - 17:00h'
}

export const SCHEDULES_MAP: { [key: string]: string } = {
  '8 a 12hs': SCHEDULES.FIRST,
  '13 a 17 hs': SCHEDULES.SECOND
}
