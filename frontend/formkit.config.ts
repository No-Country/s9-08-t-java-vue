import { es } from '@formkit/i18n'
import { type DefaultConfigOptions } from '@formkit/vue'
import { generateClasses } from '@formkit/themes'
import { genesisIcons } from '@formkit/icons'
import genesis from '@formkit/themes/tailwindcss/genesis'

const config: DefaultConfigOptions = {
  locales: { es },
  locale: 'es',
  icons: {
    ...genesisIcons
  },
  config: {
    classes: generateClasses(genesis)
  }
}

export default config
