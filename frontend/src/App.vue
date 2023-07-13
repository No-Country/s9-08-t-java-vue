<template>
  <RouterView />
</template>
<script lang="ts" setup>
import { onMounted } from 'vue'
import { useAuthStore } from './modules/auth/store/auth'

const store = useAuthStore()
onMounted(() => store.setAuthProfile())
</script>
