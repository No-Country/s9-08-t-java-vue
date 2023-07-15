<template>
  <form @submit.prevent class="w-80 rounded-[30px] bg-white px-[40px] py-[20px]">
    <h2 class="text-center text-2xl font-bold">¡Bienvenido!</h2>

    <div class="mt-3">
      <MNInput
        placeholder="Ingrese su correo"
        :show-error="emailError"
        error-msg="campo requerido"
        type="text"
        v-model="email"
      ></MNInput>
      <MNInput
        placeholder="Ingrese su contraseña"
        :show-error="passError"
        error-msg="campo requerido"
        type="password"
        v-model="password"
      ></MNInput>

      <MNButton @click="handleRegister" text="Continuar"></MNButton>
    </div>
    <div class="h-7">
      <p v-if="errorResponse" class="text-center text-sm text-red-400">Error de autenticacion</p>
    </div>

    <p class="text-center text-[1rem] text-zinc-400">ó registrate con</p>
    <div class="lines mt-2 flex w-full justify-center gap-4">
      <img src="../../../assets/img/icon-gm.svg" alt="" />
      <img src="../../../assets/img/icon-fb.svg" alt="" />
    </div>
    <p class="mt-1 text-center text-sm text-zinc-400">
      ¿Ya tienes cuenta? <RouterLink class="underline" to="/auth/login">Inicia Sesión</RouterLink>
    </p>
  </form>
</template>

<script setup lang="ts">
import MNButton from '@/components/common/MNButton.vue'
import MNInput from '@/components/common/MNInput.vue'
import { ref } from 'vue'
import { register } from '../services/authServices'
import { decodeJWT } from '../helpers/decodeJWT'
import { setAuthProfile } from '../helpers/localStorage'
import { useAuthStore } from '../store/auth'
import { useRouter } from 'vue-router'

const store = useAuthStore()
const router = useRouter()

const email = ref('')
const password = ref('')

const errorResponse = ref(false)
const passError = ref(false)
const emailError = ref(false)

const handleRegister = async () => {
  if (validInputs() == false) return
  try {
    const response = await register({ username: email.value, password: password.value })
    const profile = decodeJWT(response.token)
    setAuthProfile({ profile })
    store.setAuthProfile()
    router.push('/')
  } catch (error) {
    errorResponse.value = true
    setTimeout(() => {
      errorResponse.value = false
    }, 1000)
  }
}

const validInputs = () => {
  let areValid = true

  if (email.value.length < 3) {
    emailError.value = true
    areValid = false
    setTimeout(() => (emailError.value = false), 1000)
  }
  if (password.value.length < 6) {
    passError.value = true
    areValid = false
    setTimeout(() => (passError.value = false), 1000)
  }

  return areValid
}
</script>
<style scoped>
.lines {
  position: relative;
}
.lines::before {
  position: absolute;
  content: '';
  width: 20%;
  height: 2px;
  background: rgb(185, 185, 185);
  left: 10px;
  top: -19px;
}
.lines::after {
  position: absolute;
  content: '';
  width: 20%;
  height: 2px;
  background: rgb(185, 185, 185);
  right: 10px;
  top: -19px;
}
</style>
