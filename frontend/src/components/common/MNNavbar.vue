<template>
  <nav
    class="fixed z-10 mb-px flex h-14 w-full items-center justify-between border-b border-black bg-primary-orange px-3 md:px-10 mv:static mv:bg-white"
  >
    <div class="flex gap-5">
      <div class="flex h-14 items-center">
        <p class="text-lg font-semibold text-primary-orange">
          <router-link class="font-semibold text-white mv:text-primary-orange" to="/">
            MoveNow
          </router-link>
        </p>
      </div>
      <ul
        :class="[
          'menu-options -mv:sha z-30 flex w-full items-center gap-4 rounded-t-2xl',
          `${isOpen ? 'show-movil-menu movil-shadown' : ''}`
        ]"
      >
        <li class="items-center justify-start gap-1 -mv:flex -mv:h-14 -mv:w-full -mv:border-b-2">
          <div class="nav-icon">
            <img src="../../assets/img/navicons/mudanzas.svg" alt="" srcset="" />
          </div>
          <router-link class="p-1 text-lg font-semibold text-primary-text" to="/moving">
            Mis mudanzas
          </router-link>
        </li>
        <li class="items-center gap-1 -mv:flex -mv:h-14 -mv:w-full -mv:border-b-2">
          <div class="nav-icon">
            <img src="../../assets/img/navicons/help.svg" alt="" srcset="" />
          </div>
          <router-link class="p-1 text-lg font-semibold text-primary-text" to="/help">
            Central de ayuda
          </router-link>
        </li>
        <li
          @click="logOut"
          class="items-nav-movile items-center gap-1 -mv:flex -mv:h-14 -mv:w-full -mv:border-b-2"
        >
          <div class="nav-icon">
            <img src="../../assets/img/navicons/session.svg" alt="" srcset="" />
          </div>
          <p class="text-lg font-semibold text-primary-text">Cerrar sesion</p>
        </li>
      </ul>
    </div>
    <div class="relative flex">
      <div v-if="isAuthenticated" class="profile-icon-navbar">
        <img
          @click="profileMenu = !profileMenu"
          class="h-10 w-10 rounded-full object-cover"
          src="@/assets/img/prof.gif"
          alt=""
        />
        <div
          :class="[
            'absolute -left-56 top-12 z-10 w-64 rounded-b-3xl rounded-tl-3xl bg-white p-8 shadow-3xl',
            profileMenu == false ? 'profile-menu' : ''
          ]"
        >
          <div class="flex items-center gap-4">
            <div>
              <img class="h-20 w-20 rounded-full object-cover" src="@/assets/img/prof.gif" alt="" />
            </div>
            <div><h2 class="font-bold text-blue-900">!Hola</h2></div>
          </div>
          <ul class="mt-7 border-t-[1px] border-blue-900">
            <li class="flex h-14 items-center gap-2">
              <img src="../../assets/img/navicons/mudanzas.svg" alt="" srcset="" />
              <RouterLink to="/moving">
                <p class="font-semibold">Mis mudanzas</p>
              </RouterLink>
            </li>
            <li class="flex h-14 items-center gap-2">
              <img src="../../assets/img/navicons/help.svg" alt="" srcset="" />
              <RouterLink to="/help">
                <p class="font-semibold">Central de ayuda</p>
              </RouterLink>
            </li>
            <li @click="logOut" class="flex h-14 items-center gap-2">
              <img src="../../assets/img/navicons/session.svg" alt="" srcset="" />
              <p class="cursor-pointer font-semibold">Cerrar sesion</p>
            </li>
          </ul>
        </div>
      </div>
      <div v-else class="mr-3 flex items-center justify-center gap-2">
        <router-link to="/auth/login">Login</router-link>
        <router-link to="/auth/register">Sign Up</router-link>
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
import { useAuthStore } from '@/modules/auth/store/auth'
import { useRouter } from 'vue-router'
import { storeToRefs } from 'pinia'
import { watch } from 'vue'

const auth = storeToRefs(useAuthStore())
const isAuthenticated = ref(!!auth.store.value.profile.token)
const isOpen = ref(false)
const handleMenu = () => (isOpen.value = !isOpen.value)
const profileMenu = ref(false)
const router = useRouter()

watch(
  () => auth.store.value.profile,
  () => (isAuthenticated.value = !!auth.store.value.profile.token)
)

const logOut = () => {
  localStorage.clear()
  auth.store.value.profile = { email: '', id: 0, roles: [], token: '', username: '' }
  router.push('/')
  profileMenu.value = !profileMenu.value
}
</script>

<style>
.nav-icon,
.items-nav-movile,
.show-el,
.profile-menu {
  display: none;
}

.profile-animated {
}

@media (max-width: 775px) {
  .show-el {
    display: block;
  }
  .profile-icon-navbar {
    display: none;
  }

  .menu-options::before {
    position: relative;
    content: '';

    width: 100%;
    height: 30px;
  }

  .menu-options::after {
    position: absolute;
    content: '';
    width: 50px;
    height: 3px;
    background: rgb(142, 142, 142);
    z-index: 1;
    top: 20px;
  }

  .menu-options {
    background: #ffffff;
    position: fixed;
    height: auto;
    display: flex;
    flex-direction: column;
    left: 0;
    gap: 5px;
    padding: 15px;
    top: 50px;
    transition: 0.3s;
    box-shadow: rgba(85, 85, 85, 0.123) 10px 10px 10px;
    border: red;
    top: 100vh;
  }
  .show-movil-menu {
    transform: translateY(-100%);
    transition: 0.3s;
  }

  .movil-shadown {
    box-shadow: -1px -14px 70px 34px rgba(0, 0, 0, 0.41);
    -webkit-box-shadow: -1px -14px 70px 34px rgba(0, 0, 0, 0.41);
    -moz-box-shadow: -1px -14px 70px 34px rgba(0, 0, 0, 0.41);
  }

  .items-nav-movile {
    display: flex;
  }
  .nav-icon {
    display: block;
  }
}
</style>
