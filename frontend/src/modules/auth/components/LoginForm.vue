<template>
  <form @submit.prevent class="w-80 rounded-[30px] bg-white px-[40px] py-[20px]">
    <h2 class="text-center text-2xl font-bold">¡Hola!</h2>
    <p class="text-center text-[1rem] text-zinc-400">Incia sesión con</p>

    <div class="lines mt-2 flex w-full justify-center gap-4">
      <img src="../../../assets/img/icon-gm.svg" alt="" />
      <img src="../../../assets/img/icon-fb.svg" alt="" />
    </div>
    <p class="text-center text-[1rem] text-zinc-400">ó</p>

    <div class="mt-1">
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
      <MNButton @click="handdlelogin" text="Continuar"></MNButton>
      <div class="h-7">
        <p v-if="errorLogin" class="text-center text-sm text-red-400">Error de autenticacion</p>
      </div>
    </div>
    <div>
      <p class="text-center text-sm text-zinc-400">
        ¿No tienes cuenta? <RouterLink class="underline" to="/auth/register">Registrate</RouterLink>
      </p>
    </div>
  </form>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import MNInput from '@/components/common/MNInput.vue'
import MNButton from '@/components/common/MNButton.vue'
import { useAuthStore } from '../store/auth'
import { authenticate } from '../services/authServices'
import { getAuthProfile, setAuthProfile } from '../helpers/localStorage'
import { useRouter } from 'vue-router'
import { decodeJWT } from '../helpers/decodeJWT'

const store = useAuthStore()
const router = useRouter()
const email = ref('')
const password = ref('')

const errorLogin = ref(false)
const emailError = ref(false)
const passError = ref(false)

const handdlelogin = async () => {
  if (validInputs() == false) return
  try {
    const response = await authenticate({ username: email.value, password: password.value })
    const profile = decodeJWT(response.token)
    setAuthProfile({ profile })
    store.setAuthProfile()
    router.push('/')
  } catch (error) {
    errorLogin.value = true
    setTimeout(() => {
      errorLogin.value = false
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
  if (password.value.length < 5) {
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
  width: 40%;
  height: 2px;
  background: rgb(185, 185, 185);
  left: 10px;
  bottom: -15px;
}
.lines::after {
  position: absolute;
  content: '';
  width: 40%;
  height: 2px;
  background: rgb(185, 185, 185);
  right: 10px;
  bottom: -15px;
}
</style>
