import { MOVING_STORE_NAME } from '@/constants'
import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useMovingStore = defineStore(MOVING_STORE_NAME, () => {
  const sendingType = ref('')
  const origin = ref('')
  const destination = ref('')

  const stepOne = ref({
    sendingType,
    origin,
    destination
  })

  const setSendinType = (type: string) => (sendingType.value = type)

  return { setSendinType, sendingType, stepOne }
})
