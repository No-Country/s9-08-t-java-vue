<template>
  <form @submit.prevent class="bg-white w-80 px-[40px] py-[20px] rounded-[30px]">
    <h2 class="font-bold text-center text-2xl">¡Bienvenido!</h2>

    <div class="mt-3">
      <MNInput placeholder="Ingrese su correo"></MNInput>
      <MNInput placeholder="Ingrese su constraseña"></MNInput>

      <MNButton text="Continuar"></MNButton>
    </div>

    <p class="text-zinc-400 text-[1rem] text-center">ó registrate con</p>
    <div class="flex justify-center gap-4 w-full mt-2 lines">
      <img src="../../../assets/img/icon-gm.svg" alt="" />
      <img src="../../../assets/img/icon-fb.svg" alt="" />
    </div>
    <p class="text-zinc-400 text-sm text-center mt-1">
      ¿Ya tienes cuenta? <RouterLink class="underline" to="/auth/login">Inicia Sesión</RouterLink>
    </p>
  </form>
</template>

<script setup lang="ts">
import MNButton from '@/components/common/MNButton.vue'
import MNInput from '@/components/common/MNInput.vue'
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
