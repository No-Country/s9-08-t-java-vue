import { defineStore } from 'pinia'
import type { IProfileAuth } from '../interfaces/IProfileAuth'
import { AUTH_STORE_NAME } from '../constants'
import { getAuthProfile } from '../helpers/localStorage'
import { ref } from 'vue'

const InitialState: IProfileAuth = getAuthProfile()

export const useAuthStore = defineStore(AUTH_STORE_NAME, () => {
  const store = ref<IProfileAuth>(InitialState)

  const setToken = (token: string) => (store.value.profile.token = token)
  const setUsername = (username: string) => (store.value.profile.username = username)
  const setAuthProfile = () => (store.value.profile = getAuthProfile().profile)

  return { setToken, setAuthProfile, store, setUsername }
})
