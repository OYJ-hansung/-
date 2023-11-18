import { defineStore } from "pinia";
export const searchStore = defineStore("searchPiniaStore", {
  persist: {
    storage: sessionStorage, //쓰고싶은 스토리지(세션 또는 로컬)
  },
  state: () => ({
    isSearch: false,
    isMy: false,
    word: "",
    key: "planName"
  }),
  getters: {
    getKeyWord: (state) => {
      return state.word;
    },
    checkIsSearch: (state) => {
      return state.isSearch;
    },
    checkIsMy: (state) => {
      return state.isMy;
    },
  },
  actions: {},
});
