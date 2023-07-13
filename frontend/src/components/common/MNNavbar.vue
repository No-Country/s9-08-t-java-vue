<template>
  <nav
    class="sticky z-10 mb-px flex h-14 items-center justify-between border-b border-black px-3 md:px-10"
  >
    <div class="flex gap-5">
      <div class="flex h-14 items-center">
        <p class="text-lg font-semibold text-primary-orange">MoveNow</p>
      </div>
      <ul
        :class="[
          'mv:shadow-xl menu-options flex w-full items-center gap-5',
          `${isOpen ? 'show-movil-menu' : ''}`
        ]"
      >
        <li class="p-1 text-lg font-semibold text-primary-text">Mis mudanzas</li>
        <li class="text-lg font-semibold text-primary-text">Central de ayuda</li>
      </ul>
    </div>
    <div class="flex">
      <div class="profile-icon-navbar">
        <img class="h-10 w-10" src="@/assets/img/profile-icon.svg" alt="" />
      </div>
      <div class="icon show-el">
        <MNMenuButton @toggle="handleMenu"></MNMenuButton>
      </div>
    </div>
  </nav>
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import MNMenuButton from './MNMenuButton.vue'

const isOpen = ref(false)
const handleMenu = () => (isOpen.value = !isOpen.value)
</script>

<style>
.show-el {
  display: none;
}
@media (max-width: 550px) {
  .show-el {
    display: block;
  }
  .profile-icon-navbar {
    display: none;
  }
  .menu-options {
    background: #ffffff;
    position: absolute;
    height: auto;
    display: flex;
    flex-direction: column;
    left: -100%;
    gap: 5px;
    padding: 15px;
    top: 50px;
    transition: 0.3s;
    box-shadow: rgba(85, 85, 85, 0.123) 10px 10px 10px;
  }
  .show-movil-menu {
    transform: translateX(100%);
    transition: 0.3s;
  }
}
</style>
