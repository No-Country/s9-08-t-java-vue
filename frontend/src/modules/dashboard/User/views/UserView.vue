<template>
  <main>
    <h1>User</h1>
    {{ store.count }}
  </main>
</template>

<script setup lang="ts">
import { useCounterStore } from '../store/counter'

const store = useCounterStore()
</script>
