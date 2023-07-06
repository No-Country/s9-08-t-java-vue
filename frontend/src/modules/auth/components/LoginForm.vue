<template>
  <form @submit.prevent class="bg-white w-80 px-[40px] py-[20px] rounded-[30px]">
    <h2 class="font-bold text-center text-2xl">¡Hola!</h2>
    <p class="text-zinc-400 text-[1rem] text-center">Incia sesión con</p>

    <div class="flex justify-center gap-4 w-full mt-2 lines">
      <img src="../../../assets/img/icon-gm.svg" alt="" />
      <img src="../../../assets/img/icon-fb.svg" alt="" />
    </div>
    <p class="text-zinc-400 text-[1rem] text-center">ó</p>

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
    </div>
    <div>
      <p class="text-zinc-400 text-sm text-center">
        ¿No tienes cuenta? <RouterLink class="underline" to="/auth/register">Registrate</RouterLink>
      </p>
    </div>
  </form>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import MNInput from '@/components/common/MNInput.vue'
import MNButton from '@/components/common/MNButton.vue'

const email = ref('')
const password = ref('')

const emailError = ref(true)
const passError = ref(true)

const handdlelogin = () => {
  console.log('login')
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
