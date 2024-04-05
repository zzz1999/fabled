var ee=Object.defineProperty;var te=(i,s,e)=>s in i?ee(i,s,{enumerable:!0,configurable:!0,writable:!0,value:e}):i[s]=e;var c=(i,s,e)=>(te(i,typeof s!="symbol"?s+"":s,e),e);import{s as O,q as B,i as H,S as se,f as g,U as $,V as ae,e as C,c as I,b as R,o as K,u as F,h as T,W as ie,X as ne,Y as re,Z as le,C as U,v as w,R as v}from"./scheduler.CN_jtChM.js";import{S as j,i as M,a as m,g as oe,t as p,c as ce,h as b,b as q,d as D,m as E,e as z,f as k}from"./index.DLY4uaOq.js";import{c as ue,f as W,a as X,P as J,S as de,T as fe}from"./Toggle.D4b1DYOk.js";import{P,R as A}from"./skill-store.BRKl53yh.js";import{w as _}from"./entry.DXxUocus.js";function Y(i){let s,e,t,a,n,r,u,d;const h=i[5].default,l=ae(h,i,i[4],null);return{c(){s=C("div"),e=C("div"),t=C("div"),l&&l.c(),this.h()},l(o){s=I(o,"DIV",{class:!0});var f=R(s);e=I(f,"DIV",{class:!0});var V=R(e);t=I(V,"DIV",{class:!0});var N=R(t);l&&l.l(N),N.forEach(g),V.forEach(g),f.forEach(g),this.h()},h(){K(t,"class","wrapper svelte-sv0lqh"),K(e,"class","modal-content svelte-sv0lqh"),F(e,"--width",i[1]),K(s,"class","backdrop svelte-sv0lqh")},m(o,f){H(o,s,f),T(s,e),T(e,t),l&&l.m(t,null),r=!0,u||(d=ie(ue.call(null,e,i[2])),u=!0)},p(o,f){l&&l.p&&(!r||f&16)&&ne(l,h,o,o[4],r?le(h,o[4],f,null):re(o[4]),null),f&2&&F(e,"--width",o[1])},i(o){r||(m(l,o),o&&U(()=>{r&&(a||(a=b(e,W,{y:-200},!0)),a.run(1))}),o&&U(()=>{r&&(n||(n=b(s,X,{},!0)),n.run(1))}),r=!0)},o(o){p(l,o),o&&(a||(a=b(e,W,{y:-200},!1)),a.run(0)),o&&(n||(n=b(s,X,{},!1)),n.run(0)),r=!1},d(o){o&&g(s),l&&l.d(o),o&&a&&a.end(),o&&n&&n.end(),u=!1,d()}}}function he(i){let s,e,t,a,n=i[0]&&Y(i);return{c(){n&&n.c(),s=B()},l(r){n&&n.l(r),s=B()},m(r,u){n&&n.m(r,u),H(r,s,u),e=!0,t||(a=se(window,"keyup",i[3]),t=!0)},p(r,[u]){r[0]?n?(n.p(r,u),u&1&&m(n,1)):(n=Y(r),n.c(),m(n,1),n.m(s.parentNode,s)):n&&(oe(),p(n,1,1,()=>{n=null}),ce())},i(r){e||(m(n),e=!0)},o(r){p(n),e=!1},d(r){r&&g(s),n&&n.d(r),t=!1,a()}}}function me(i,s,e){let{$$slots:t={},$$scope:a}=s,{width:n="auto"}=s,{open:r=!1}=s;const u=$(),d=l=>{e(0,r=!1),u("close"),l==null||l.stopPropagation()},h=l=>{l.key=="Escape"&&(l.preventDefault(),l.stopPropagation(),d())};return i.$$set=l=>{"width"in l&&e(1,n=l.width),"open"in l&&e(0,r=l.open),"$$scope"in l&&e(4,a=l.$$scope)},[r,n,d,h,a,t]}class Ie extends j{constructor(s){super(),M(this,s,me,he,O,{width:1,open:0})}}class Re extends P{constructor(e,t=!1,a=!1){super("mechanic",e,a);c(this,"iconKey","");c(this,"countsAsCast",!0);this.isParent=t}getData(e=!1){const t={};return t["icon-key"]=this.iconKey,t.counts=this.countsAsCast,this.data.filter(a=>e||a.meetsRequirements(this)).forEach(a=>{const n=a.getData();Object.keys(n).forEach(r=>t[r]=n[r])}),t}deserialize(e){super.deserialize(e);const t=e.data;t&&this.data.forEach(a=>a.deserialize(t)),this.iconKey=t["icon-key"],this.countsAsCast=t.counts===void 0?!0:t.counts,e.children&&Object.keys(e.children).length>0&&this.setComponents(A.deserializeComponents(e.children))}}class pe{constructor(){c(this,"requirements",{});c(this,"requireValue",(s,e)=>(this.requirements[s]=e,this));c(this,"meetsRequirements",s=>{for(const e in this.requirements)if(!this.requirements[e].includes(s.getData(!0)[e]))return!1;return!0});c(this,"meetsPreviewRequirements",s=>{for(const e in this.requirements)if(!this.requirements[e].includes(s.getRawPreviewData()[e]))return!1;return!0})}}const y=class y extends P{constructor(e,t=!1){super("condition",e,t);c(this,"iconKey","")}getData(e=!1){const t={};return t["icon-key"]=this.iconKey,this.data.filter(a=>e||a.meetsRequirements(this)).forEach(a=>{const n=a.getData();Object.keys(n).forEach(r=>t[r]=n[r])}),t}deserialize(e){super.deserialize(e);const t=e.data;this.iconKey=t["icon-key"],t&&this.data.forEach(a=>a.deserialize(t)),e.children&&Object.keys(e.children).length>0&&this.setComponents(A.deserializeComponents(e.children))}};c(y,"new",()=>new y({name:"null"}));let Z=y;function ge(i){let s,e,t,a,n;function r(l){i[3](l)}function u(l){i[4](l)}function d(l){i[5](l)}let h={};return i[0].selected!==void 0&&(h.selected=i[0].selected),i[0].value!==void 0&&(h.data=i[0].value),i[0].multiple!==void 0&&(h.multiple=i[0].multiple),s=new de({props:h}),w.push(()=>k(s,"selected",r)),w.push(()=>k(s,"data",u)),w.push(()=>k(s,"multiple",d)),{c(){q(s.$$.fragment)},l(l){D(s.$$.fragment,l)},m(l,o){E(s,l,o),n=!0},p(l,o){const f={};!e&&o&1&&(e=!0,f.selected=l[0].selected,v(()=>e=!1)),!t&&o&1&&(t=!0,f.data=l[0].value,v(()=>t=!1)),!a&&o&1&&(a=!0,f.multiple=l[0].multiple,v(()=>a=!1)),s.$set(f)},i(l){n||(m(s.$$.fragment,l),n=!0)},o(l){p(s.$$.fragment,l),n=!1},d(l){z(s,l)}}}function _e(i){let s,e;return s=new J({props:{label:i[1],tooltip:i[2],$$slots:{default:[ge]},$$scope:{ctx:i}}}),{c(){q(s.$$.fragment)},l(t){D(s.$$.fragment,t)},m(t,a){E(s,t,a),e=!0},p(t,[a]){const n={};a&2&&(n.label=t[1]),a&4&&(n.tooltip=t[2]),a&129&&(n.$$scope={dirty:a,ctx:t}),s.$set(n)},i(t){e||(m(s.$$.fragment,t),e=!0)},o(t){p(s.$$.fragment,t),e=!1},d(t){z(s,t)}}}function be(i,s,e){let{data:t}=s,{name:a=""}=s,{tooltip:n=void 0}=s;const r=$();function u(l){i.$$.not_equal(t.selected,l)&&(t.selected=l,e(0,t))}function d(l){i.$$.not_equal(t.value,l)&&(t.value=l,e(0,t))}function h(l){i.$$.not_equal(t.multiple,l)&&(t.multiple=l,e(0,t))}return i.$$set=l=>{"data"in l&&e(0,t=l.data),"name"in l&&e(1,a=l.name),"tooltip"in l&&e(2,n=l.tooltip)},i.$$.update=()=>{i.$$.dirty&1&&t&&r("save")},[t,a,n,u,d,h]}class we extends j{constructor(s){super(),M(this,s,be,_e,O,{data:0,name:1,tooltip:2})}}class L extends pe{constructor(e,t,a,n,r=!1){super();c(this,"component",we);c(this,"dataSource");c(this,"data",{selected:"",value:[],multiple:!1});c(this,"name","");c(this,"key","");c(this,"tooltip");c(this,"init",()=>{this.dataSource&&(this.data.value=this.dataSource()),!this.data.selected&&this.data.value.length>0&&!this.data.multiple&&(this.data.selected=this.data.value[0])});c(this,"setTooltip",e=>(this.tooltip=e,this));c(this,"clone",()=>new L(this.name,this.key,[...this.data.value],this.data.selected));c(this,"getData",()=>{const e={};return e[this.key]=this.data.selected,e});c(this,"getSummary",()=>this.data.selected instanceof Array?this.data.selected.join(", "):this.data.selected);c(this,"deserialize",e=>{this.data.selected=e[this.key],this.data.multiple&&!(this.data.selected instanceof Array)?this.data.selected=[this.data.selected]:!this.data.multiple&&this.data.selected instanceof Array&&(this.data.selected=this.data.selected[0])});this.name=e,this.key=t,typeof a=="function"?this.dataSource=a:this.data.value=a,r&&(this.data.selected=[]),n&&(this.data.selected=n),this.data.multiple=r}}const S=class S extends P{constructor(e,t=!1){super("target",e,t);c(this,"iconKey","")}getData(e=!1){const t={};return t["icon-key"]=this.iconKey,this.data.filter(a=>e||a.meetsRequirements(this)).forEach(a=>{const n=a.getData();Object.keys(n).forEach(r=>t[r]=n[r])}),t}deserialize(e){super.deserialize(e);const t=e.data;this.iconKey=t["icon-key"],t&&this.data.forEach(a=>a.deserialize(t)),e.children&&Object.keys(e.children).length>0&&this.setComponents(A.deserializeComponents(e.children))}};c(S,"new",()=>new S({name:"null"}));let G=S;function ve(i){let s,e,t;function a(r){i[3](r)}let n={};return i[0]!==void 0&&(n.data=i[0]),s=new fe({props:n}),w.push(()=>k(s,"data",a)),{c(){q(s.$$.fragment)},l(r){D(s.$$.fragment,r)},m(r,u){E(s,r,u),t=!0},p(r,u){const d={};!e&&u&1&&(e=!0,d.data=r[0],v(()=>e=!1)),s.$set(d)},i(r){t||(m(s.$$.fragment,r),t=!0)},o(r){p(s.$$.fragment,r),t=!1},d(r){z(s,r)}}}function ke(i){let s,e;return s=new J({props:{label:i[1],tooltip:i[2],$$slots:{default:[ve]},$$scope:{ctx:i}}}),{c(){q(s.$$.fragment)},l(t){D(s.$$.fragment,t)},m(t,a){E(s,t,a),e=!0},p(t,[a]){const n={};a&2&&(n.label=t[1]),a&4&&(n.tooltip=t[2]),a&33&&(n.$$scope={dirty:a,ctx:t}),s.$set(n)},i(t){e||(m(s.$$.fragment,t),e=!0)},o(t){p(s.$$.fragment,t),e=!1},d(t){z(s,t)}}}function ye(i,s,e){let{data:t}=s,{name:a=""}=s,{tooltip:n=void 0}=s;const r=$();function u(d){t=d,e(0,t)}return i.$$set=d=>{"data"in d&&e(0,t=d.data),"name"in d&&e(1,a=d.name),"tooltip"in d&&e(2,n=d.tooltip)},i.$$.update=()=>{i.$$.dirty&1&&(t||!t)&&r("save")},[t,a,n,u]}class Ke extends j{constructor(s){super(),M(this,s,ye,ke,O,{data:0,name:1,tooltip:2})}}const Oe=(()=>{let i=!0;{const a=localStorage.getItem("show-summary-items");i=a!==null?a==="true":i}const{subscribe:s,set:e,update:t}=_(i);return{subscribe:s,set:a=>(localStorage.setItem("show-summary-items",String(a)),e(a)),update:t}})(),$e=(()=>{let i=!0;i=localStorage.getItem("use-symbols")==="true";const{subscribe:s,set:e,update:t}=_(i);return{subscribe:s,set:a=>(localStorage.setItem("use-symbols",String(a)),e(a)),update:t}})(),je=(()=>{let i=!0;{const a=localStorage.getItem("animation-enabled");i=a!==null?a==="true":i}const{subscribe:s,set:e,update:t}=_(i);return{subscribe:s,set:a=>(localStorage.setItem("animation-enabled",String(a)),e(a)),update:t}})(),Me=(i,{delay:s=0,duration:e=500}={})=>{const a=getComputedStyle(i).width.match(/([\d.]+)(.*)/);if(!a)return{delay:s,duration:e,css:r=>`max-width: unset; width: ${r*100}%;`};const n=Number.parseFloat(a[1]);return{delay:s,duration:e,css:r=>`max-width: unset; width: ${r*n}${a[2]}; min-width: unset;`}},Pe=(i,{delay:s=0,duration:e=500})=>{const a=getComputedStyle(i).height.match(/([\d.]+)(.*)/);if(!a)return{delay:s,duration:e,css:r=>`max-height: unset; height: ${r*100}%;`};const n=Number.parseFloat(a[1]);return{delay:s,duration:e,css:r=>`max-height: unset; height: ${r*n}${a[2]}; min-height: unset;`}},Q=_(void 0),x=_(void 0),Ae=(i,s)=>{Q.set(i),s&&x.set(s)},Ve=()=>{Q.set(void 0),x.set(void 0)};export{Ke as B,L as D,Ie as M,Re as P,pe as R,G as a,Z as b,Ve as c,Oe as d,je as e,Me as f,Q as g,x as m,Ae as o,Pe as s,$e as u};
