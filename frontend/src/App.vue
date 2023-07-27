<template>
  <RouterView />
</template>
<script lang="ts" setup>
import { onMounted } from 'vue'
import { useAuthStore } from './modules/auth/store/auth'
import { useRouter } from 'vue-router'

const store = useAuthStore()
//const router = useRouter()

onMounted(() => {
  store.setAuthProfile()
  //if (store.store.profile.token.length > 0) router.push('/')
})
</script>
