var ee=Object.defineProperty;var te=(n,s,t)=>s in n?ee(n,s,{enumerable:!0,configurable:!0,writable:!0,value:t}):n[s]=t;var u=(n,s,t)=>(te(n,typeof s!="symbol"?s+"":s,t),t);import{b as O,R as j,P as Q,d as se,f as V,e as F}from"./skill-store.CjgU62P6.js";import{s as M,Z as A,D as w,Y as v,B as J,i as U,u as ae,f as g,q as ie,e as C,c as I,b as $,o as K,r as T,h as Y,$ as ne,w as oe,x as le,y as re,J as Z}from"./scheduler.DBH14GOu.js";import{S as R,i as P,b as D,d as E,m as q,t as m,a as p,e as z,f as y,g as ce,c as de,o as b}from"./index.Dyda3I9Q.js";import{S as ue,T as fe,c as he}from"./Toggle.DGE3zJCw.js";import{w as _}from"./entry.BmMX_jHA.js";class Ie extends O{constructor(t,e=!1,a=!1){super("mechanic",t,a);u(this,"iconKey","");u(this,"countsAsCast",!0);this.isParent=e}getData(t=!1){const e={};return e["icon-key"]=this.iconKey,e.counts=this.countsAsCast,this.data.filter(a=>t||a.meetsRequirements(this)).forEach(a=>{const i=a.getData();Object.keys(i).forEach(o=>e[o]=i[o])}),e}deserialize(t){super.deserialize(t);const e=t.data;e&&this.data.forEach(a=>a.deserialize(e)),this.iconKey=e["icon-key"],this.countsAsCast=e.counts===void 0?!0:e.counts,t.children&&Object.keys(t.children).length>0&&this.setComponents(j.deserializeComponents(t.children))}}const k=class k extends O{constructor(t,e=!1){super("condition",t,e);u(this,"iconKey","")}getData(t=!1){const e={};return e["icon-key"]=this.iconKey,this.data.filter(a=>t||a.meetsRequirements(this)).forEach(a=>{const i=a.getData();Object.keys(i).forEach(o=>e[o]=i[o])}),e}deserialize(t){super.deserialize(t);const e=t.data;this.iconKey=e["icon-key"],e&&this.data.forEach(a=>a.deserialize(e)),t.children&&Object.keys(t.children).length>0&&this.setComponents(j.deserializeComponents(t.children))}};u(k,"new",()=>new k({name:"null"}));let G=k;function me(n){let s,t,e,a,i;function o(l){n[3](l)}function c(l){n[4](l)}function d(l){n[5](l)}let h={};return n[0].selected!==void 0&&(h.selected=n[0].selected),n[0].value!==void 0&&(h.data=n[0].value),n[0].multiple!==void 0&&(h.multiple=n[0].multiple),s=new ue({props:h}),w.push(()=>y(s,"selected",o)),w.push(()=>y(s,"data",c)),w.push(()=>y(s,"multiple",d)),{c(){D(s.$$.fragment)},l(l){E(s.$$.fragment,l)},m(l,r){q(s,l,r),i=!0},p(l,r){const f={};!t&&r&1&&(t=!0,f.selected=l[0].selected,v(()=>t=!1)),!e&&r&1&&(e=!0,f.data=l[0].value,v(()=>e=!1)),!a&&r&1&&(a=!0,f.multiple=l[0].multiple,v(()=>a=!1)),s.$set(f)},i(l){i||(m(s.$$.fragment,l),i=!0)},o(l){p(s.$$.fragment,l),i=!1},d(l){z(s,l)}}}function pe(n){let s,t;return s=new Q({props:{label:n[1],tooltip:n[2],$$slots:{default:[me]},$$scope:{ctx:n}}}),{c(){D(s.$$.fragment)},l(e){E(s.$$.fragment,e)},m(e,a){q(s,e,a),t=!0},p(e,[a]){const i={};a&2&&(i.label=e[1]),a&4&&(i.tooltip=e[2]),a&129&&(i.$$scope={dirty:a,ctx:e}),s.$set(i)},i(e){t||(m(s.$$.fragment,e),t=!0)},o(e){p(s.$$.fragment,e),t=!1},d(e){z(s,e)}}}function ge(n,s,t){let{data:e}=s,{name:a=""}=s,{tooltip:i=void 0}=s;const o=A();function c(l){n.$$.not_equal(e.selected,l)&&(e.selected=l,t(0,e))}function d(l){n.$$.not_equal(e.value,l)&&(e.value=l,t(0,e))}function h(l){n.$$.not_equal(e.multiple,l)&&(e.multiple=l,t(0,e))}return n.$$set=l=>{"data"in l&&t(0,e=l.data),"name"in l&&t(1,a=l.name),"tooltip"in l&&t(2,i=l.tooltip)},n.$$.update=()=>{n.$$.dirty&1&&e&&o("save")},[e,a,i,c,d,h]}class _e extends R{constructor(s){super(),P(this,s,ge,pe,M,{data:0,name:1,tooltip:2})}}class W extends se{constructor(t,e,a,i,o=!1){super();u(this,"component",_e);u(this,"dataSource");u(this,"data",{selected:"",value:[],multiple:!1});u(this,"name","");u(this,"key","");u(this,"tooltip");u(this,"init",()=>{this.dataSource&&(this.data.value=this.dataSource()),!this.data.selected&&this.data.value.length>0&&!this.data.multiple&&(this.data.selected=this.data.value[0])});u(this,"setTooltip",t=>(this.tooltip=t,this));u(this,"clone",()=>new W(this.name,this.key,[...this.data.value],this.data.selected));u(this,"getData",()=>{const t={};return t[this.key]=this.data.selected,t});u(this,"getSummary",()=>this.data.selected instanceof Array?this.data.selected.join(", "):this.data.selected);u(this,"deserialize",t=>{this.data.selected=t[this.key],this.data.multiple&&!(this.data.selected instanceof Array)?this.data.selected=[this.data.selected]:!this.data.multiple&&this.data.selected instanceof Array&&(this.data.selected=this.data.selected[0])});this.name=t,this.key=e,typeof a=="function"?this.dataSource=a:this.data.value=a,o&&(this.data.selected=[]),i&&(this.data.selected=i),this.data.multiple=o}}const S=class S extends O{constructor(t,e=!1){super("target",t,e);u(this,"iconKey","")}getData(t=!1){const e={};return e["icon-key"]=this.iconKey,this.data.filter(a=>t||a.meetsRequirements(this)).forEach(a=>{const i=a.getData();Object.keys(i).forEach(o=>e[o]=i[o])}),e}deserialize(t){super.deserialize(t);const e=t.data;this.iconKey=e["icon-key"],e&&this.data.forEach(a=>a.deserialize(e)),t.children&&Object.keys(t.children).length>0&&this.setComponents(j.deserializeComponents(t.children))}};u(S,"new",()=>new S({name:"null"}));let H=S;function be(n){let s,t,e;function a(o){n[3](o)}let i={};return n[0]!==void 0&&(i.data=n[0]),s=new fe({props:i}),w.push(()=>y(s,"data",a)),{c(){D(s.$$.fragment)},l(o){E(s.$$.fragment,o)},m(o,c){q(s,o,c),e=!0},p(o,c){const d={};!t&&c&1&&(t=!0,d.data=o[0],v(()=>t=!1)),s.$set(d)},i(o){e||(m(s.$$.fragment,o),e=!0)},o(o){p(s.$$.fragment,o),e=!1},d(o){z(s,o)}}}function we(n){let s,t;return s=new Q({props:{label:n[1],tooltip:n[2],$$slots:{default:[be]},$$scope:{ctx:n}}}),{c(){D(s.$$.fragment)},l(e){E(s.$$.fragment,e)},m(e,a){q(s,e,a),t=!0},p(e,[a]){const i={};a&2&&(i.label=e[1]),a&4&&(i.tooltip=e[2]),a&33&&(i.$$scope={dirty:a,ctx:e}),s.$set(i)},i(e){t||(m(s.$$.fragment,e),t=!0)},o(e){p(s.$$.fragment,e),t=!1},d(e){z(s,e)}}}function ve(n,s,t){let{data:e}=s,{name:a=""}=s,{tooltip:i=void 0}=s;const o=A();function c(d){e=d,t(0,e)}return n.$$set=d=>{"data"in d&&t(0,e=d.data),"name"in d&&t(1,a=d.name),"tooltip"in d&&t(2,i=d.tooltip)},n.$$.update=()=>{n.$$.dirty&1&&(e||!e)&&o("save")},[e,a,i,c]}class $e extends R{constructor(s){super(),P(this,s,ve,we,M,{data:0,name:1,tooltip:2})}}function L(n){let s,t,e,a,i,o,c,d;const h=n[5].default,l=ie(h,n,n[4],null);return{c(){s=C("div"),t=C("div"),e=C("div"),l&&l.c(),this.h()},l(r){s=I(r,"DIV",{class:!0});var f=$(s);t=I(f,"DIV",{class:!0});var B=$(t);e=I(B,"DIV",{class:!0});var N=$(e);l&&l.l(N),N.forEach(g),B.forEach(g),f.forEach(g),this.h()},h(){K(e,"class","wrapper svelte-sv0lqh"),K(t,"class","modal-content svelte-sv0lqh"),T(t,"--width",n[1]),K(s,"class","backdrop svelte-sv0lqh")},m(r,f){U(r,s,f),Y(s,t),Y(t,e),l&&l.m(e,null),o=!0,c||(d=ne(he.call(null,t,n[2])),c=!0)},p(r,f){l&&l.p&&(!o||f&16)&&oe(l,h,r,r[4],o?re(h,r[4],f,null):le(r[4]),null),f&2&&T(t,"--width",r[1])},i(r){o||(m(l,r),r&&Z(()=>{o&&(a||(a=b(t,V,{y:-200},!0)),a.run(1))}),r&&Z(()=>{o&&(i||(i=b(s,F,{},!0)),i.run(1))}),o=!0)},o(r){p(l,r),r&&(a||(a=b(t,V,{y:-200},!1)),a.run(0)),r&&(i||(i=b(s,F,{},!1)),i.run(0)),o=!1},d(r){r&&g(s),l&&l.d(r),r&&a&&a.end(),r&&i&&i.end(),c=!1,d()}}}function ye(n){let s,t,e,a,i=n[0]&&L(n);return{c(){i&&i.c(),s=J()},l(o){i&&i.l(o),s=J()},m(o,c){i&&i.m(o,c),U(o,s,c),t=!0,e||(a=ae(window,"keyup",n[3]),e=!0)},p(o,[c]){o[0]?i?(i.p(o,c),c&1&&m(i,1)):(i=L(o),i.c(),m(i,1),i.m(s.parentNode,s)):i&&(ce(),p(i,1,1,()=>{i=null}),de())},i(o){t||(m(i),t=!0)},o(o){p(i),t=!1},d(o){o&&g(s),i&&i.d(o),e=!1,a()}}}function ke(n,s,t){let{$$slots:e={},$$scope:a}=s,{width:i="auto"}=s,{open:o=!1}=s;const c=A(),d=l=>{t(0,o=!1),c("close"),l==null||l.stopPropagation()},h=l=>{l.key=="Escape"&&(l.preventDefault(),l.stopPropagation(),d())};return n.$$set=l=>{"width"in l&&t(1,i=l.width),"open"in l&&t(0,o=l.open),"$$scope"in l&&t(4,a=l.$$scope)},[o,i,d,h,a,e]}class Ke extends R{constructor(s){super(),P(this,s,ke,ye,M,{width:1,open:0})}}const Oe=(()=>{let n=!0;{const a=localStorage.getItem("show-summary-items");n=a!==null?a==="true":n}const{subscribe:s,set:t,update:e}=_(n);return{subscribe:s,set:a=>(localStorage.setItem("show-summary-items",String(a)),t(a)),update:e}})(),je=(()=>{let n=!0;n=localStorage.getItem("use-symbols")==="true";const{subscribe:s,set:t,update:e}=_(n);return{subscribe:s,set:a=>(localStorage.setItem("use-symbols",String(a)),t(a)),update:e}})(),Me=(()=>{let n=!0;{const a=localStorage.getItem("animation-enabled");n=a!==null?a==="true":n}const{subscribe:s,set:t,update:e}=_(n);return{subscribe:s,set:a=>(localStorage.setItem("animation-enabled",String(a)),t(a)),update:e}})(),Ae=(n,{delay:s=0,duration:t=500}={})=>{const a=getComputedStyle(n).width.match(/([\d.]+)(.*)/);if(!a)return{delay:s,duration:t,css:o=>`max-width: unset; width: ${o*100}%;`};const i=Number.parseFloat(a[1]);return{delay:s,duration:t,css:o=>`max-width: unset; width: ${o*i}${a[2]}; min-width: unset;`}},Re=(n,{delay:s=0,duration:t=500})=>{const a=getComputedStyle(n).height.match(/([\d.]+)(.*)/);if(!a)return{delay:s,duration:t,css:o=>`max-height: unset; height: ${o*100}%;`};const i=Number.parseFloat(a[1]);return{delay:s,duration:t,css:o=>`max-height: unset; height: ${o*i}${a[2]}; min-height: unset;`}},X=_(void 0),x=_(void 0),Pe=(n,s)=>{X.set(n),s&&x.set(s)},Be=()=>{X.set(void 0),x.set(void 0)};export{$e as B,W as D,Ke as M,Ie as P,H as a,G as b,Be as c,Oe as d,Me as e,Ae as f,X as g,x as m,Pe as o,Re as s,je as u};
