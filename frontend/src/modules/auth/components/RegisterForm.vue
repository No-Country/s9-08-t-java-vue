<template>
  <form @submit.prevent class="w-80 rounded-[30px] bg-white px-[40px] py-[20px]">
    <h2 class="text-center text-2xl font-bold">¡Bienvenido!</h2>

    <div class="mt-3">
      <MNInput placeholder="Ingrese su correo"></MNInput>
      <MNInput placeholder="Ingrese su constraseña"></MNInput>

      <MNButton text="Continuar"></MNButton>
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
