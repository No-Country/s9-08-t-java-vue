import { MOVING_STORE_NAME } from '@/constants'
import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useMovingStore = defineStore(MOVING_STORE_NAME, () => {
  const sendingType = ref('')
  const origin = ref('')
  const destination = ref('')
  const date = ref('')

  const movingSize = ref('')
  const vehicle = ref()

  const stepOne = ref({
    sendingType,
    origin,
    destination,
    date
  })

  const stepTwo = ref()

  const setValuesStepOne = (originForm: string, destinationForm: string, dateForm: string) => {
    origin.value = originForm
    destination.value = destinationForm
    date.value = dateForm
  }
  const setSendinType = (type: string) => (sendingType.value = type)

  return { setSendinType, sendingType, stepOne, setValuesStepOne, movingSize }
})
