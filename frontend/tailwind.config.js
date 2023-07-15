/** @type {import('tailwindcss').Config} */
const FormKitVariants = require('@formkit/themes/tailwindcss')

export default {
  content: [
    './index.html',
    './src/**/*.{vue,js,ts,jsx,tsx}',
    './node_modules/@formkit/themes/dist/tailwindcss/genesis/index.cjs'
  ],
  theme: {
    fontFamily: {
      montserrat: ['Montserrat', 'sans-serif']
    },
    extend: {
      colors: {
        'primary-blue': '#2825E4',
        'primary-orange': '#FF6935',
        'primary-text': '#100E34',
        'blue-night': '#291D89',
        'light-blue': '#E1ECFF',
        'gray-card': '#FBFAFA'
      },
      screen: {
        mv: { max: '550px' }
      },
      borderRadius: {
        '4xl': '50px'
      },
      boxShadow: {
        '3xl': '0 5px 15px rgba(0, 0, 0, 0.35)'
      }
    }
  },
  plugins: [FormKitVariants]
}
