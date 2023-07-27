import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useCheckoutStore = defineStore('checkout', () => {
  const name = ref('')
  const number = ref('')
  const valid = ref('')
  const date = ref('')
  const isVisa = ref(false)

  return {
    name,
    number,
    valid,
    date,
    isVisa
  }
})
