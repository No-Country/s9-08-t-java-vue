import { AUTH_STORE_NAME } from '@/modules/auth/constants'
import { getAuthProfile } from '@/modules/auth/helpers/localStorage'
import axios from 'axios'

import.meta.env.VITE_API_URL

const http = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  headers: {
    'Content-Type': 'application/json',
    Accept: 'application/json'
  }
})

http.interceptors.request.use(
  (config) => {
    // Verificar si la solicitud no es de autenticación
    if (!config.url!.includes('/auth')) {
      const token = getAuthProfile().profile.token
      if (token) {
        config.headers['Authorization'] = `Bearer ${token}`
      }
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

export { http }
