import { defineStore } from 'pinia'
import { ref, toRef } from 'vue'
import { HOME_STORE_NAME } from '../constants'

export const useHomeStore = defineStore(HOME_STORE_NAME, () => {
  const sendingType = ref('')
  const origin = ref('')
  const destination = ref('')

  const obj = ref({
    sendingType,
    origin,
    destination
  })

  const setSendinType = (type: string) => (sendingType.value = type)

  return { setSendinType, sendingType, obj }
})
