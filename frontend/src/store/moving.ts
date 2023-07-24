import { MOVING_STORE_NAME } from '@/lib/constants'
import { defineStore } from 'pinia'
import { reactive, ref } from 'vue'

export const useMovingStore = defineStore(MOVING_STORE_NAME, () => {
  const sendingType = ref('')
  const origin = ref('')
  const destination = ref('')
  const date = ref('')

  const stepOne = reactive({
    sendingType,
    origin,
    destination,
    date
  })

  const movingSize = ref('')
  const vehicleType = ref('')
  const helperFlag = ref(false)
  const helpers = ref([])

  const stepTwo = ref()

  const setValuesStepOne = (originForm: string, destinationForm: string, dateForm: string) => {
    origin.value = originForm
    destination.value = destinationForm
    date.value = dateForm
  }
  const setSendinType = (type: string) => (sendingType.value = type)

  return { setSendinType, sendingType, stepOne, setValuesStepOne, movingSize, vehicleType }
})
