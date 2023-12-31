import camioneta from '@/assets/img/camioneta.webp'
import camion from '@/assets/img/camion.webp'
import camion2 from '@/assets/img/camion-2.webp'
import { VEHICLES } from '../constants'

export const flota = [
  {
    id: 1,
    name: 'CAMIONETA PICK UP',
    short: 'S10 CABINA SIMPLE',
    info1: 'Capacidad de carga hasta de 1000kg',
    info2: 'Disponible solo para envios',
    selectable: false,
    img: camioneta,
    type: VEHICLES.PICK_UP
  },
  {
    id: 2,
    name: 'CAMIÓN',
    short: 'S10 CABINA SIMPLE',
    info1: 'Capacidad de carga hasta de 1500kg',
    info2: 'Disponible solo para mudanzas',
    selectable: true,
    img: camion,
    type: VEHICLES.TRUCK
  },
  {
    id: 3,
    name: 'CAMIÓN PESADO',
    short: 'MERCEDEZ FAMILIA 360',
    info1: 'Capacidad de carga mayor a 1500kg',
    info2: 'Disponible solo para mudanzas',
    selectable: true,
    img: camion2,
    type: VEHICLES.HEAVY_TRUCK
  }
]
