<template>

  <div class="border-[1px] rounded-[40px] border-primary-blue w-72 h-64 relative p-4">
    <div class="absolute top-[-75px] w-52 left-0 right-0 m-auto flex justify-center">
      <img class="object-cover h-28 w-52 " :src="prop.img" alt="">
    </div>
    <div class="text-center mt-6 text-blue-night">
      <p class="font-bold ">{{ prop.name }}</p>
      <p>{{ prop.short }}</p>
    </div>
    <div class="w-full mt-1 bg-blue-100  rounded-[15px] flex text-sm p-3 text-zinc-700">
      <div class="w-1/2 flex">
        <span>
          <svg class="inline" width="14" height="13" viewBox="0 0 14 13" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M11.6666 3.25L5.24992 9.20833L2.33325 6.5" stroke="black" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
          </svg>
        </span>
        <p>{{ prop.info1 }}</p>
      </div>
      <div class="w-1/2 flex">
        <span>
          <svg class="inline" width="14" height="13" viewBox="0 0 14 13" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M11.6666 3.25L5.24992 9.20833L2.33325 6.5" stroke="black" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
          </svg>
        </span>
        <p>{{ prop.info2 }}</p>
      </div>
    </div>
    <div class="w-28 m-auto mt-3" color="bg-orange-400">
     <MNButton text="Contratar" class="bg-primary-orange rounded-lg py-1 hover:brightness-90"></MNButton> 
    </div>
  </div>

</template>

<script lang="ts" setup>
  import MNButton from '@/components/common/MNButton.vue'
  import {type IFleetCard} from '../interfaces/IFleetCard'

  interface FleetCardProp {
    prop : IFleetCard
  }

  const props = defineProps<FleetCardProp>()
</script>