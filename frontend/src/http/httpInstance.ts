import axios from 'axios'

import.meta.env.VITE_API_URL

const http = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  headers: {
    'Content-Type': 'application/json',
    Accept: 'application/json'
  }
})

export { http }
