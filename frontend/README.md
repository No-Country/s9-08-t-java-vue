# MoveNow

This template should help get you started developing with Vue 3 in Vite.

## Customize configuration

See [Vite Configuration Reference](https://vitejs.dev/config/).

## Project Setup

```sh
npm install
```

### Compile and Hot-Reload for Development

```sh
npm run dev
```

### Type-Check, Compile and Minify for Production

```sh
npm run build
```

### Lint with [ESLint](https://eslint.org/)

```sh
npm run lint
```

## Doc

components/common/MNDropdownInput.vue

```html
<template>
  <main class="flex justify-center items-center h-screen">
    <MNDropdownInput @selected="setType" :items="['Enviar paquete', 'Mudanza']" :text="typeSelected"></MNDropdownInput>
  </main>
</template>

<script setup lang="ts">
import MNDropdownInput from '@/components/common/MNDropdownInput.vue';
import { ref } from 'vue';

const typeSelected = ref('Tipo de envio')
const setType = (type: string) => typeSelected.value = type
  
</script>
```

components/common/MNInput.vue

- props that can be used in MNInput.vue
```js
<template>
  <MNInput v-model="email" border="" class=""></MNInput>
</template>

{
  modelValue: String,
  type: {
    default: 'text',
    type: String
  },
  placeholder: {
    type: String,
    required: true
  },
  errorMsg: {
    type: String,
    default: 'error'
  },
  showError: {
    type: Boolean,
    default: false,
    required: false
  },
  border: {
    type: String,
    default: ''
  },
  class: {
    type: String,
    default: ''
  }
})
``` 